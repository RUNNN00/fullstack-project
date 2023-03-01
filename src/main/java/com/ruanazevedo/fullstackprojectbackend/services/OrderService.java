package com.ruanazevedo.fullstackprojectbackend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruanazevedo.fullstackprojectbackend.domain.Order;
import com.ruanazevedo.fullstackprojectbackend.repositories.OrderRepository;
import com.ruanazevedo.fullstackprojectbackend.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repos;
	
	public Order findById(Integer id) {
		Optional<Order> obj = repos.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", tipo: " + Order.class.getName()));
	}
}
