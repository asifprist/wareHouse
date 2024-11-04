package com.example.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.entity.Leads;
import com.example.leadsTargetClent.ExcelHelper;
import com.example.repository.LeadRepository;
import com.example.serviceimpl.LeadServiceImpl;

@Service
public class LeadService implements LeadServiceImpl{
	
	
	  @Autowired
	    private LeadRepository leadsRepository;


	    public List<Leads> saveLeadsFromExcel(MultipartFile file) throws IOException {
	        List<Leads> leadsList = ExcelHelper.excelToLeads(file.getInputStream());
	        return leadsRepository.saveAll(leadsList);
	    }

	    public List<Leads> getAllLeads() {
	        return leadsRepository.findAll();
	    }

//		@Override
//		public Leads updateFile(Leads updatefile) {
//			Leads save = leadsRepository.save(updatefile);
//			return save;
//		}

		@Override
		public Leads deleteById(String fileDelId) {
			leadsRepository.deleteById(fileDelId);
			return null;
		}

		@Override
		public Optional<Leads> getById(String fileId) {
			Optional<Leads> byId = leadsRepository.findById(fileId);
			return byId;
		}

}
//@Override
//public List<Leads> saveLeadsFromExcel(MultipartFile file) throws IOException {
//      List<Leads> leadsList = ExcelHelper.excelToLeads(file.getInputStream());
//      List<Leads> saveAll = leadsRepository.saveAll(leadsList);
//      return saveAll;
//  }