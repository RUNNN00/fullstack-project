package com.ruanazevedo.fullstackprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruanazevedo.fullstackprojectbackend.domain.Adress;
import com.ruanazevedo.fullstackprojectbackend.domain.City;
import com.ruanazevedo.fullstackprojectbackend.domain.Client;
import com.ruanazevedo.fullstackprojectbackend.domain.enums.ClientType;
import com.ruanazevedo.fullstackprojectbackend.dto.ClientDTO;
import com.ruanazevedo.fullstackprojectbackend.dto.ClientNewDTO;
import com.ruanazevedo.fullstackprojectbackend.repositories.AdressRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.CityRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.ClientRepository;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.DataIntegrityException;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repos;
	
	@Autowired
	private CityRepository cityRepos;
	
	@Autowired
	private AdressRepository adressRepos;
	
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
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = repos.save(obj);
		adressRepos.saveAll(obj.getAdresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateClient(newObj, obj);
		return repos.save(newObj);
	}
	
	public void deleteById(Integer id) {
		Client obj = findById(id);
		try {
			repos.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir o cliente " + obj.getName() + " porque há entidades relacionadas");
		}
	}
	
	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}
	
	public Client fromDTO(ClientNewDTO objDto) {
		Client client = new Client(null, objDto.getName(), objDto.getEmail(), objDto.getCpfOrCnpj(), ClientType.toEnum(objDto.getType()));
		City city = cityRepos.findById(objDto.getCityId()).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + objDto.getCityId() + " do tipo: " + City.class.getName()));
		Adress adress = new Adress(null, objDto.getPublicPlace(), objDto.getNum(), objDto.getComplement(), objDto.getDistrict(), objDto.getCep(), city, client);
		client.getAdresses().add(adress);
		client.getPhones().add(objDto.getPhone1());
		if (objDto.getPhone2() != null)
			client.getPhones().add(objDto.getPhone2());
		if (objDto.getPhone3() != null)
			client.getPhones().add(objDto.getPhone3());
		
		return client;
	}
	
	private void updateClient(Client newObj, Client oldObj ) {
		newObj.setName(oldObj.getName());
		newObj.setEmail(oldObj.getEmail());
	}
}
