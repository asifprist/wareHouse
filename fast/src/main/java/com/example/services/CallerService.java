package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CallFeedback;
import com.example.repository.CallerRepo;
import com.example.serviceimpl.CallServiceImpl;

@Service
public class CallerService implements CallServiceImpl{
@Autowired
private CallerRepo callerRepo;
	@Override
	public CallFeedback save(CallFeedback client) {
		CallFeedback save = callerRepo.save(client);
		return save;
	}

	@Override
	public List<CallFeedback> getAll() {
		List<CallFeedback> all = callerRepo.findAll();
		return all;
	}

//	@Override
//	public CallFeedback updateCaller(CallFeedback updateCl) {
//		CallFeedback updatecall = callerRepo.save(updateCl);
//		return updatecall;
//	}

	@Override
	public CallFeedback deleteCallId(String clId) {
		callerRepo.deleteById(clId);
		return null;
	}

	@Override
	public CallFeedback getCallId(String clId) {
		CallFeedback byId = callerRepo.getById(clId);
		return byId;
	}

//	@Override
//	public CallFeedback deleteCallerId(String clDelId) {
//		callerRepo.deleteBycallerId(clDelId);
//		return null;
//		
//	}
//
//	@Override
//	public CallFeedback getBycallerId(String callerId) {
//		CallFeedback bycallerId = callerRepo.getBycallerId(callerId);
//		return bycallerId;
//	}

}
