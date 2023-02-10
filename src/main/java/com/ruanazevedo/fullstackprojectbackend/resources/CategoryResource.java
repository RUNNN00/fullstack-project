package com.ruanazevedo.fullstackprojectbackend.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@GetMapping
	public ResponseEntity<Category> findAll() {
		Category cat = new Category(1, "Informática");
		return ResponseEntity.ok(cat);
	}
}
