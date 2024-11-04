package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CallFeedback;
@Repository
public interface CallerRepo extends JpaRepository<CallFeedback, String>{
	
	public CallFeedback getById(String clId);
	
	//public void deleteBycallerId(String clDelId);
	
	//public CallFeedback getBycallerId(String callerId);

}
