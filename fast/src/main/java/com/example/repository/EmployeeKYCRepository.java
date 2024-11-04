package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.AadharImage;
import com.example.entity.AccountPassbook;
import com.example.entity.EmployeeKYC;
import com.example.entity.PanImage;
@Repository
public interface EmployeeKYCRepository extends JpaRepository<EmployeeKYC, String>{
	
	public EmployeeKYC getById(String empId);

	public EmployeeKYC save(PanImage panimg);

	public EmployeeKYC save(AccountPassbook acpass);

	public EmployeeKYC save(AadharImage aadimg);
	
}
