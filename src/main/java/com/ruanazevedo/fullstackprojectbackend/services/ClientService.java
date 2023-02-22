package com.ruanazevedo.fullstackprojectbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanazevedo.fullstackprojectbackend.domain.Client;
import com.ruanazevedo.fullstackprojectbackend.repositories.ClientRepository;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repos;
	
	public Client findById(Integer id) {
		Optional<Client> obj = repos.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " do tipo: " + Client.class.getName()));
	}
}
