package com.ruanazevedo.fullstackprojectbackend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;
import com.ruanazevedo.fullstackprojectbackend.repositories.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	public List<Category> findAll() {
		List<Category> list = repo.findAll();
		return list;
	}
	
	public Category findById(Integer id) {
		Optional<Category> obj = repo.findById(id);
		return obj.orElseThrow();
	}
}
