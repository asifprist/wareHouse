package com.example.entity;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@Document
@Entity
public class FileDocument {

	@Id
	private String id;
	private String fileName;
	private byte[] fileData;
	//private String filePath;
	//ArrayList<Path> pathList = new ArrayList<>();
//	public void setFileData(byte[] bytes) {
//		// TODO Auto-generated method stub
//		
//	}
	 
}
