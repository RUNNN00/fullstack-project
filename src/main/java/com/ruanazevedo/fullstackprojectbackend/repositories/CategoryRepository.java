package com.ruanazevedo.fullstackprojectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
