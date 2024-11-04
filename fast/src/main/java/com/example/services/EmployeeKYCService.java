package com.example.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.AadharImage;
import com.example.entity.AccountPassbook;
import com.example.entity.EmployeeKYC;
import com.example.entity.PanImage;
import com.example.repository.EmployeeKYCRepository;
import com.example.serviceimpl.EmployeeKYCServiceImpl;
import com.example.util.ExcelUtil;

@Service
public class EmployeeKYCService implements EmployeeKYCServiceImpl {

	@Autowired
	private EmployeeKYCRepository emprepo;

//	@Override
//	public EmployeeKYC save(EmployeeKYC empkyc) {
//		EmployeeKYC saves = emprepo.save(empkyc);
//		return saves;
//	}

	@Override
	public List<EmployeeKYC> getAll() {
		List<EmployeeKYC> all = emprepo.findAll();
		return all;
	}

//	@Override
//	public EmployeeKYC updateEmployeeKYC(EmployeeKYC updatekyc) {
//		EmployeeKYC update = emprepo.save(updatekyc);
//		return update;
//	}
//	@Override
//	public EmployeeKYC updateEmployeeKYC(EmployeeKYC updatekyc, MultipartFile aadharFile, MultipartFile panFile,
//			MultipartFile passbookFile) {
//		EmployeeKYC savedEmployeeKYC = emprepo.save(updatekyc);
//		// Save AadharImage
//		AadharImage aadharImage = new AadharImage();
//
//		String aadharFileId = generateFileId();
//		aadharImage.setFileId(aadharFileId);
//		// aadharImage.setFileId(aadharFile.getName());
//		try {
//			aadharImage.setUrl(aadharFile.getBytes());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		savedEmployeeKYC.getAadharImage().add(aadharImage);
//
//		// Save PanImage
//		PanImage panImage = new PanImage();
//		// panImage.setFileId(panFile.getName());
//		String panFileId = generateFileId();
//		panImage.setFileId(panFileId);
//		try {
//			panImage.setUrl(panFile.getBytes());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		savedEmployeeKYC.getPanImage().add(panImage);
//
//		// Save AccountPassbook
//		AccountPassbook passbookImage = new AccountPassbook();
//		// passbookImage.setFileId(panFile.getName());
//		String passbookFileId = generateFileId(); 
//		passbookImage.setFileId(passbookFileId);
//		try {
//			passbookImage.setUrl(passbookFile.getBytes());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		savedEmployeeKYC.getPassbookImage().add(passbookImage);
//
//		
//		emprepo.save(savedEmployeeKYC);
//
//		return savedEmployeeKYC;
//
//	}

//	private String generateFileId() {
//		return new ObjectId().toString();
//	}

	

	@Override
	public EmployeeKYC deleteByEmpId(String kycId) {
		emprepo.deleteById(kycId);
		return null;
	}

	@Override
	public EmployeeKYC getById(String empId) {
		EmployeeKYC byId = emprepo.getById(empId);
		return byId;
	}
//-------------------download excel file-----------------
	@Override
	public ByteArrayInputStream getDataDownloaded() throws IOException {
		List<EmployeeKYC> empkyc = emprepo.findAll();
		ByteArrayInputStream data = ExcelUtil.dataToExcel(empkyc);
		return data;

	}
	// -------------all empkyc , pan,aadhar and passbook image save along
	// with------------

	@Override
	public EmployeeKYC saveEmpKyc(EmployeeKYC empkyc, MultipartFile aadharFile, MultipartFile panFile,
			MultipartFile passbookFile) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public EmployeeKYC saveEmpKyc(EmployeeKYC empkyc, MultipartFile aadharFile, MultipartFile panFile,
//			MultipartFile passbookFile) {
//		// Save EmployeeKYC details
//		EmployeeKYC savedEmployeeKYC = emprepo.save(empkyc);
//
//		// Save AadharImage
//		AadharImage aadharImage = new AadharImage();
//
//		String aadharFileId = generateFileId();
//		aadharImage.setFileId(aadharFileId);
//		// aadharImage.setFileId(aadharFile.getName());
//		try {
//			aadharImage.setUrl(aadharFile.getBytes());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		savedEmployeeKYC.getAadharImage().add(aadharImage);
//
//		// Save PanImage
//		PanImage panImage = new PanImage();
//		// panImage.setFileId(panFile.getName());
//		String panFileId = generateFileId();
//		panImage.setFileId(panFileId);
//		try {
//			panImage.setUrl(panFile.getBytes());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		savedEmployeeKYC.getPanImage().add(panImage);
//
//		// Save AccountPassbook
//		AccountPassbook passbookImage = new AccountPassbook();
//		// passbookImage.setFileId(panFile.getName());
//		String passbookFileId = generateFileId(); 
//		passbookImage.setFileId(passbookFileId);
//		try {
//			passbookImage.setUrl(passbookFile.getBytes());
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//		savedEmployeeKYC.getPassbookImage().add(passbookImage);
//
//		
//		emprepo.save(savedEmployeeKYC);
//
//		return savedEmployeeKYC;
//
//	}
//
//	private String generateFileId() {
//		return new ObjectId().toString();
//	}


}
