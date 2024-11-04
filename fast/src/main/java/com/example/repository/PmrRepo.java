package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.ClientDetail;
@Repository
public interface PmrRepo extends JpaRepository<ClientDetail, String>{
	
	//@Query()
	//ClientDetail getByName();
	
	public ClientDetail getById(String clId);
	
	public ClientDetail getByclientName(String clname);
	
	public void deleteById(String clId);
	
	public void deleteByclientName(String cldelname);
	

}
