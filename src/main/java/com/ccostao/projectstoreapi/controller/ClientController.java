package com.ccostao.projectstoreapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccostao.projectstoreapi.domain.Client;
import com.ccostao.projectstoreapi.service.ClientService;

@RestController
@RequestMapping(value="/clients")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Client>> clientList() {
		List<Client> client = clientService.findAll();
		return ResponseEntity.ok().body(client);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Client> findById(@PathVariable Integer id) {
		Client client = clientService.find(id);
		return ResponseEntity.ok().body(client);
	}
}
