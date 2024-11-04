package com.example.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Document(collection = "targetclients")

@Entity(name="targetclient")
public class Leads {

	private String id;
	private String date;
	private String customerName;
	private String location;
	private String mobileNumber;
	private String companyName;
	private String loanAmount;
	private String panNumber;
	private String salary;
	private String emailId;

}
