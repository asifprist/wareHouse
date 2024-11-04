package com.example.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.EmployeeKYC;


public interface EmployeeKYCServiceImpl {

	//public EmployeeKYC save(EmployeeKYC empkyc);

	public List<EmployeeKYC> getAll();

//	public EmployeeKYC updateEmployeeKYC(EmployeeKYC updatekyc);

	public EmployeeKYC deleteByEmpId(String kycId);

	public EmployeeKYC getById(String empId);

	ByteArrayInputStream getDataDownloaded() throws IOException;

	EmployeeKYC saveEmpKyc(EmployeeKYC empkyc, MultipartFile aadharFile, MultipartFile panFile,
			MultipartFile passbookFile);

	//EmployeeKYC updateEmployeeKYC(EmployeeKYC updatekyc,MultipartFile aadharFile,MultipartFile panFile,MultipartFile passbookFile);

}
