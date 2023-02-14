package com.ruanazevedo.fullstackprojectbackend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;
import com.ruanazevedo.fullstackprojectbackend.repositories.CategoryRepository;

@Configuration
public class Test implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepos;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		Category cat3 = new Category(null, "Eletrônicos");
		Category cat4 = new Category(null, "Cozinha");
		
		categoryRepos.saveAll(Arrays.asList(cat1, cat2, cat3, cat4));
	}
}