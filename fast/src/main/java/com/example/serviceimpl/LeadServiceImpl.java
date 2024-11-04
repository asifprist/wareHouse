package com.example.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Leads;

public interface LeadServiceImpl {

	public List<Leads> saveLeadsFromExcel(MultipartFile file) throws IOException;

	public Leads deleteById(String fileDelId);

	public Optional<Leads> getById(String fileId);

	//public Leads updateFile(Leads updatefile);

}
