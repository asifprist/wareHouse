package com.example.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.entity.EmployeeKYC;

public class ExcelUtil {
    public static String HEADER[] = {"LOCATION","EMP-NO","BIO-NO","NAME","EMAIL-ID","DESIGNATION","REPORTING","MANAGER-NAME","SALARY","DATE-OF-JOINING",
    		"BANK-NAME","ACCOUNT-NUMBER","IFSC","AADHAR-NUMBER","PAN-NUMBER","HUSBAND-AND-FATHERS-NAME","CURRENT-ADDRESS","PERMANENT-ADDRESS","RELATION","RELATION-NUMBER","DATE-OF-BIRTH",
    		"CONTACT-NUMBER","ALTERNATE-NUMBER","ADDED-BY","ID"
};

    public static String SHEET_NAME = "sheetForEmployeeKYC";
    public static ByteArrayInputStream dataToExcel(List<EmployeeKYC> empKYCList) throws IOException {
        Workbook workbook  = new XSSFWorkbook();

        ByteArrayOutputStream byteArrayOutputStream  = new ByteArrayOutputStream();
        try {
        Sheet sheet = workbook.createSheet(SHEET_NAME);
        Row row = sheet.createRow(0);
        

          for (int i  =0; i< HEADER.length;i++){

           org.apache.poi.ss.usermodel.Cell cell = row.createCell(i);
           cell.setCellValue(HEADER[i]);
        }

           int rowIndex = 1;
          for (EmployeeKYC p :empKYCList){
              Row row1 = sheet.createRow(rowIndex);
              rowIndex++;
              row1.createCell(0).setCellValue(p.getLocation());
              row1.createCell(1).setCellValue(p.getEmpNo());
              row1.createCell(2).setCellValue(p.getBioNo());
              row1.createCell(3).setCellValue(p.getName());
              row1.createCell(4).setCellValue(p.getEmailId());
              row1.createCell(5).setCellValue(p.getDesignation());
              row1.createCell(6).setCellValue(p.getReporting());
              row1.createCell(7).setCellValue(p.getManagerName());
              row1.createCell(8).setCellValue(p.getSalary());
              row1.createCell(9).setCellValue(p.getDateOfJoining());
              row1.createCell(10).setCellValue(p.getBankName());
              row1.createCell(11).setCellValue(p.getAccountNumber());
              row1.createCell(12).setCellValue(p.getIfsc());
              row1.createCell(13).setCellValue(p.getAadharNumber());
              row1.createCell(14).setCellValue(p.getPanNumber());
              row1.createCell(15).setCellValue(p.getHusbandAndFathersName());
              row1.createCell(16).setCellValue(p.getCurrentAddress());
              row1.createCell(17).setCellValue(p.getPermanentAddress());
              row1.createCell(18).setCellValue(p.getRelation());
              row1.createCell(19).setCellValue(p.getRelationNumber());
              row1.createCell(20).setCellValue(p.getDateOfBirth());
              row1.createCell(21).setCellValue(p.getContactNumber());
              row1.createCell(22).setCellValue(p.getAlternateNumber());
              row1.createCell(23).setCellValue(p.getAddedBy());
              row1.createCell(24).setCellValue(p.getId());
              
          }
            workbook.write(byteArrayOutputStream);
            return  new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
       finally {
            workbook.close();
            byteArrayOutputStream.close();
        }
    }

}
