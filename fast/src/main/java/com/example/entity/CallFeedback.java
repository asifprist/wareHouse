package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Document(collection ="caller")
@Entity
public class CallFeedback {
	@Id
	private String id;
	private String date;
	private String remarks;
	
	//private Date dates;
	

}
