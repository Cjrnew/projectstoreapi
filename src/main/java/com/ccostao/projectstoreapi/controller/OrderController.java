package com.ccostao.projectstoreapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccostao.projectstoreapi.domain.Order;
import com.ccostao.projectstoreapi.service.OrderService;

@RestController
@RequestMapping(value="/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Order>> orderList() {
		List<Order> order = orderService.findAll();
		return ResponseEntity.ok().body(order);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Order> findById(@PathVariable Integer id) {
		Order order = orderService.find(id);
		return ResponseEntity.ok().body(order);
	}
}
