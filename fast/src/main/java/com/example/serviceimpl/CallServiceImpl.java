package com.example.serviceimpl;

import java.util.List;

import com.example.entity.CallFeedback;

public interface CallServiceImpl {

	public CallFeedback save(CallFeedback client);

	public List<CallFeedback> getAll();

	//public CallFeedback updateCaller(CallFeedback updateCl);
	
	public CallFeedback deleteCallId(String clId);

	//CallFeedback getCallerId(String clId);

	CallFeedback getCallId(String clId);

	// updateCaller(CallFeedback updateCl);

//	public CallFeedback getCallerId(String clId);

	//public CallFeedback deleteCallerId(String clDelId);
	
	//public CallFeedback getBycallerId(String callerId);

}
