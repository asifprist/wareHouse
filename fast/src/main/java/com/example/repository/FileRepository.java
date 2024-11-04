package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.FileDocument;
@Repository
public interface FileRepository extends JpaRepository<FileDocument, String>{
	
	
	
	public void deleteById(String clDelId);
	
	public FileDocument getByfileName(String fileName);
	
	public FileDocument deleteByfileName(String fildel);
	
	

}
