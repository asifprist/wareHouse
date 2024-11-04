package com.example.serviceimpl;

import java.util.List;

import com.example.entity.ClientDetail;

public interface ClientServiceimpl {
	
	public ClientDetail save(ClientDetail client);
	
	public List<ClientDetail> getAll();
	
	public ClientDetail getClientId(String clId);
	
	public ClientDetail getClientName(String clname);
	
	public ClientDetail deleteClientId(String clId);
	
	//public ClientDetail updateCllient(ClientDetail updateCl);
	
	public ClientDetail deleteClientName(String cldelname);

	//void deleteClientId(String cldel);

	//ClientDetail getClientId(String clId);

}
