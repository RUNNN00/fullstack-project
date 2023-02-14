package com.ruanazevedo.fullstackprojectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanazevedo.fullstackprojectbackend.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
