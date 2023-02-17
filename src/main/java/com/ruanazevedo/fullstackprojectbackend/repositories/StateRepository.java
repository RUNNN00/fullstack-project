package com.ruanazevedo.fullstackprojectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanazevedo.fullstackprojectbackend.domain.State;

public interface StateRepository extends JpaRepository<State, Integer> {

}
