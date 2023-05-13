package com.ruanazevedo.fullstackprojectbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;
import com.ruanazevedo.fullstackprojectbackend.domain.Client;
import com.ruanazevedo.fullstackprojectbackend.domain.Product;
import com.ruanazevedo.fullstackprojectbackend.repositories.CategoryRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.ProductRepository;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repos;
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	public Product findById(Integer id) {
		return repos.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " do tipo: " + Client.class.getName()));
	}
	
	public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String directionOrder) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(directionOrder), orderBy);
		List<Category> categories = repositoryCategory.findAllById(ids);
		return repos.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
	}
}
