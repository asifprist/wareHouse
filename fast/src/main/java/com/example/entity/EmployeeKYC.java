package com.example.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
//@Document(collection ="employeekycs")
@Entity
public class EmployeeKYC {
	@Id
	
    private String id;
	@NotEmpty
	private String location;
	@NotEmpty
	private String empNo;
	@NotEmpty
	private String bioNo;
	@NotEmpty
	private String name;
	@Email
	private String emailId;
	@NotEmpty
	private String designation;
	@NotEmpty
	private String reporting;
	@NotEmpty
	private String managerName;
	@NotEmpty
	private String salary;
	@NotEmpty
	private String dateOfJoining;
	@NotEmpty
	private String bankName;
	@NotEmpty
	private String accountNumber;
	@NotEmpty
	private String ifsc;
	@NotEmpty
	private String aadharNumber;
	@NotEmpty
	private String panNumber;
	@NotEmpty
	private String husbandAndFathersName;
	@NotEmpty
	private String currentAddress;
	@NotEmpty
	private String permanentAddress;
	@NotEmpty
	private String relation;
	@NotEmpty
	private String relationNumber;
	@NotEmpty
	private String dateOfBirth;
	@NotEmpty
	private String contactNumber;
	@NotEmpty
	private String alternateNumber;
	@NotEmpty
	private String addedBy;

	//@DBRef
	private List<AccountPassbook> passbookImage = new ArrayList<>();
	//@DBRef
    private List<AadharImage> aadharImage = new ArrayList<>();
	//@DBRef
    private List<PanImage> panImage = new ArrayList<>();
}
	//private UserFCD use;
	
	

