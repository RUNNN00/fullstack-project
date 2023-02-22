package com.ruanazevedo.fullstackprojectbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanazevedo.fullstackprojectbackend.domain.Client;
import com.ruanazevedo.fullstackprojectbackend.domain.Product;
import com.ruanazevedo.fullstackprojectbackend.repositories.ProductRepository;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repos;
	
	public Product findById(Integer id) {
		return repos.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " do tipo: " + Client.class.getName()));
	}
}
