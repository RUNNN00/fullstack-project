package com.ruanazevedo.fullstackprojectbackend.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;
import com.ruanazevedo.fullstackprojectbackend.domain.City;
import com.ruanazevedo.fullstackprojectbackend.domain.Product;
import com.ruanazevedo.fullstackprojectbackend.domain.State;
import com.ruanazevedo.fullstackprojectbackend.repositories.CategoryRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.CityRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.ProductRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.StateRepository;

@Configuration
public class Test implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepos;
	
	@Autowired
	private ProductRepository productRepos;
	
	@Autowired
	private StateRepository stateRepos;
	
	@Autowired
	private CityRepository cityRepos;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product prod1 = new Product(null, "Computador", 2000.00);
		Product prod2 = new Product(null, "Impressora", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);
		
		categoryRepos.saveAll(Arrays.asList(cat1, cat2));
		productRepos.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		cat1.getProducts().addAll(Arrays.asList(prod1,prod2, prod3));
		cat2.getProducts().add(prod2);
		
		prod1.getCategories().add(cat1);
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().add(cat1);
		
		stateRepos.saveAll(Arrays.asList(st1, st2));
		cityRepos.saveAll(Arrays.asList(c1, c2, c3));
	}
}