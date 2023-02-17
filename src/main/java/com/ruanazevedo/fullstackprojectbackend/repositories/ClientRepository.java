package com.ruanazevedo.fullstackprojectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanazevedo.fullstackprojectbackend.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
