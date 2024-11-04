package com.example.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.FileDocument;

public interface FileServiceImpl {

	public FileDocument uploadFile(MultipartFile file);

	public List<FileDocument> getAll();

	public FileDocument deleteById(String fileDelId);

	public Optional<FileDocument> getById(String fileId);

	public FileDocument updateFile(FileDocument updatefile);

	FileDocument getFileById(String fileId);
	
	public FileDocument getByfileName(String fileName);
	
	public FileDocument deleteByfileName(String fildel);
	
	

}
