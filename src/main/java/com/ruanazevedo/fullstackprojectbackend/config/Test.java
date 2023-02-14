package com.ruanazevedo.fullstackprojectbackend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;
import com.ruanazevedo.fullstackprojectbackend.domain.Product;
import com.ruanazevedo.fullstackprojectbackend.repositories.CategoryRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.ProductRepository;

@Configuration
public class Test implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepos;
	
	@Autowired
	private ProductRepository productRepos;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product prod1 = new Product(null, "Computador", 2000.00);
		Product prod2 = new Product(null, "Impressora", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(prod1,prod2, prod3));
		cat2.getProducts().add(prod2);
		
		prod1.getCategories().add(cat1);
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().add(cat1);
		
		categoryRepos.saveAll(Arrays.asList(cat1, cat2));
		productRepos.saveAll(Arrays.asList(prod1, prod2, prod3));
	}
}