package com.example.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@Document
@Entity
public class ProfileImage {

	@Id
	private String id;
	private String username;
	private byte[] imagePath;

}
