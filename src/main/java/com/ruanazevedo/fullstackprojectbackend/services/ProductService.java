package com.ruanazevedo.fullstackprojectbackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanazevedo.fullstackprojectbackend.domain.Product;
import com.ruanazevedo.fullstackprojectbackend.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repos;
	
	public Product findById(Integer id) {
		return repos.findById(id).orElseThrow();
	}
}
