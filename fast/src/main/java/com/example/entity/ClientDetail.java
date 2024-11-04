package com.example.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//pmr details
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Document(collection ="pmrs")
@Entity
public class ClientDetail {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY) // Use GenerationType.AUTO if preferred
	private String id;
	private String clientName;
	private String loanStatus;
	private String bankName;
	private String paymentAmount;
	private String contact;
	private String age;
	private String location;
	private String remarks;
	private String date;
	
	
	

}
