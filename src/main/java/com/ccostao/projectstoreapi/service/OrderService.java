package com.ccostao.projectstoreapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccostao.projectstoreapi.domain.Order;
import com.ccostao.projectstoreapi.repository.OrderRepository;
import com.ccostao.projectstoreapi.service.exception.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository categoryRepository;
	
	public List<Order> findAll() {
		List<Order> category = categoryRepository.findAll();
		return category;
	}
	
	public Order find(Integer id) {
		Optional<Order> category = categoryRepository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id + ", Type: " + Order.class.getName()));
	}
	
}
