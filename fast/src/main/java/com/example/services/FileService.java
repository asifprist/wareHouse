package com.example.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.FileDocument;
import com.example.repository.FileRepository;
import com.example.serviceimpl.FileServiceImpl;

import io.jsonwebtoken.io.IOException;

@Service
public class FileService implements FileServiceImpl {
	@Autowired
	private FileRepository fileRepository;

	@Autowired
	public FileService(FileRepository fileRepository) {
		this.fileRepository = fileRepository;
	}

//	@Value("${server.folder}")
//	private String FILE_DIRECTORY;
	// save file in sever path and name save in database
	// String originalFilename = file.getOriginalFilename();
	// String fileExtension =
	// originalFilename.substring(originalFilename.lastIndexOf(".") +
	// 1).toLowerCase();
	// Save the file to the specified directory
// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
// Calendar calendarVariable = Calendar.getInstance();
// String strCurrentDate = dateFormat.format(calendarVariable.getTime());
// String strFileName = "0000000000000000" + user.getUserId();
// String strFileNamePattern = strCurrentDate + strFileName.substring(strFileName.length() - 10);
// String fileName = strFileNamePattern + "." + fileExtension;
// String filePath = FILE_DIRECTORY + fileName;
	@Override
	public FileDocument getFileById(String fileId) {
		return fileRepository.findById(fileId).orElse(null);
	}
	///------------------------------------



	@Override
	public FileDocument uploadFile(MultipartFile file) throws IOException {
		FileDocument fileDocument = new FileDocument();
		fileDocument.setFileName(file.getOriginalFilename());
		try {

			fileDocument.setFileData(file.getBytes());
		} catch (java.io.IOException e) {

			e.printStackTrace();
		}
		return fileRepository.save(fileDocument);
	}

//-------------------------------------------------
	@Override
	public List<FileDocument> getAll() {
		List<FileDocument> all = fileRepository.findAll();
		return all;
	}

	@Override
	public FileDocument deleteById(String fileDelId) {
		fileRepository.deleteById(fileDelId);
		return null;
	}

	@Override
	public Optional<FileDocument> getById(String fileId) {
		Optional<FileDocument> byId = fileRepository.findById(fileId);
		return byId;
	}

	@Override
	public FileDocument updateFile(FileDocument updatefile) {
		FileDocument updates = fileRepository.save(updatefile);
		return updates;
	}

	@Override
	public FileDocument getByfileName(String fileName) {
		FileDocument byfileName = fileRepository.getByfileName(fileName);
		return byfileName;
	}

	@Override
	public FileDocument deleteByfileName(String fildel) {
		FileDocument deleteByfileName = fileRepository.deleteByfileName(fildel);
		return deleteByfileName;
	}

}
