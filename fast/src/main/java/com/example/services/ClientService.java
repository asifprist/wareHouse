package com.example.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.ClientDetail;
import com.example.repository.PmrRepo;
import com.example.serviceimpl.ClientServiceimpl;

@Service
public class ClientService implements ClientServiceimpl {

	@Autowired
	private PmrRepo clientRepo;
	
	@Override
	public ClientDetail save(ClientDetail client) {
		ClientDetail save = clientRepo.save(client);
		return save;
	}

	@Override
	public List<ClientDetail> getAll() {
		List<ClientDetail> findAll = clientRepo.findAll();
		return findAll;
	}

//	@Override
//	public ClientDetail getClientId(Integer clId) {
//		ClientDetail entityClient = clientRepo.findById(clId).get();
//		return entityClient;
//	}
//
//	@Override
//	public void deleteClientId(Integer clId) {
//		clientRepo.deleteById(clId);
//		
//	}

//	@Override
//	public ClientDetail updateCllient(ClientDetail updateCl) {
//		ClientDetail updates = clientRepo.save(updateCl);
//		return updates;
//	}

	@Override
	public ClientDetail getClientId(String clId) {
		ClientDetail byId = clientRepo.getById(clId);
		return byId;
	}

	@Override
	public ClientDetail deleteClientId(String cldel) {
		clientRepo.deleteById(cldel);
		return null;
		
	}

	@Override
	public ClientDetail getClientName(String clname) {
		ClientDetail byclientName = clientRepo.getByclientName(clname);
		return byclientName;
	}

	@Override
	public ClientDetail deleteClientName(String cldelname) {
		clientRepo.deleteByclientName(cldelname);
		return null;
	}



//	@Override
//	public EntityClient getClientName(String clname) {
//		EntityClient byname = clientRepo.getByname(clname);
//		return byname;
//	}

}
