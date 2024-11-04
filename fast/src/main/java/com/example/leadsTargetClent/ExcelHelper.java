package com.example.leadsTargetClent;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import com.example.entity.Leads;

@Component
public class ExcelHelper {

	public static List<Leads> excelToLeads(InputStream inputStream) throws IOException {
		Workbook workbook = WorkbookFactory.create(inputStream);
		List<Leads> leadsList = new ArrayList<>();

		Sheet sheet = workbook.getSheetAt(0); // Assuming data is in the first sheet

		// Iterator<Row> rowIterator =
		java.util.Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			// Skip the header row
			if (row.getRowNum() == 0) {
				continue;
			}

			Leads leads = new Leads();
			// Iterator<Cell> cellIterator =
			java.util.Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				int columnIndex = cell.getColumnIndex();

				switch (cell.getCellType()) {
				case STRING:
					handleStringCell(columnIndex, cell, leads);
					break;
				case NUMERIC:
					handleNumericCell(columnIndex, cell, leads);
					break;
				// Add cases for other cell types...

				default:
					// Handle unexpected cell type
					break;
				}
			}

			leadsList.add(leads);
		}

		workbook.close();
		return leadsList;
	}

	private static void handleStringCell(int columnIndex, Cell cell, Leads leads) {
		String cellValue = cell.getStringCellValue();
		setLeadsField(columnIndex, cellValue, leads);
	}

	private static void handleNumericCell(int columnIndex, Cell cell, Leads leads) {
		if (cell.getCellStyle().getDataFormatString().contains("yy")) {
			// Handle date format
			Date date = cell.getDateCellValue();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			String formattedDate = dateFormat.format(date);
			setLeadsField(columnIndex, formattedDate, leads);
		} else {
			// Handle other numeric values
			double numericValue = cell.getNumericCellValue();
			String cellValue;

			// Special handling for mobileNumber, loanAmount, and salary columns
			if (columnIndex == 4 || columnIndex == 6 || columnIndex == 8) {
				// Format as a string without scientific notation
				cellValue = String.format("%.0f", numericValue);
			} else {
				// Default handling for other numeric values
				cellValue = String.valueOf(numericValue);
			}

			setLeadsField(columnIndex, cellValue, leads);
		}
	}

	private static void setLeadsField(int columnIndex, String cellValue, Leads leads) {
		switch (columnIndex) {
//		case 0:
//			leads.setId(cellValue);
//			break;
		case 1:
			leads.setDate(cellValue);
			break;
		case 2:
			leads.setCustomerName(cellValue);
			break;
		case 3:
			leads.setLocation(cellValue);
			break;
		case 4:
			leads.setMobileNumber(cellValue);
			break;
		case 5:
			leads.setCompanyName(cellValue);
			break;
		case 6:
			leads.setLoanAmount(cellValue);
			break;
		case 7:
			leads.setPanNumber(cellValue);
			break;
		case 8:
			leads.setSalary(cellValue);
			break;
		case 9:
			leads.setEmailId(cellValue);
			break;
		default:
			
			break;
		}
	}

}
