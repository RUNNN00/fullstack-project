package com.ruanazevedo.fullstackprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ruanazevedo.fullstackprojectbackend.domain.Client;
import com.ruanazevedo.fullstackprojectbackend.dto.ClientDTO;
import com.ruanazevedo.fullstackprojectbackend.repositories.ClientRepository;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.DataIntegrityException;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repos;
	
	public List<Client> findAll() {
		List<Client> list = repos.findAll();
		return list;
	}
	
	public Client findById(Integer id) {
		Optional<Client> obj = repos.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " do tipo: " + Client.class.getName()));
	}
	
	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String directionOrder) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directionOrder), orderBy);
		return repos.findAll(pageRequest);
	}
	
	public Client insert(Client obj) {
		obj.setId(null);
		return repos.save(obj);
	}
	
	public Client update(Client obj) {
		findById(obj.getId());
		return repos.save(obj);
	}
	
	public void deleteById(Integer id) {
		findById(id);
		try {
			repos.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um cliente que possui endereços");
		}
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
}
