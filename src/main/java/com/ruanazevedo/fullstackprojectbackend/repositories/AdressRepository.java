package com.ruanazevedo.fullstackprojectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanazevedo.fullstackprojectbackend.domain.Adress;

public interface AdressRepository extends JpaRepository<Adress, Integer> {

}
