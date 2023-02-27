package com.ruanazevedo.fullstackprojectbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ruanazevedo.fullstackprojectbackend.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
