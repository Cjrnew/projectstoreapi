package com.ccostao.projectstoreapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ccostao.projectstoreapi.domain.Address;
import com.ccostao.projectstoreapi.domain.City;
import com.ccostao.projectstoreapi.domain.Client;
import com.ccostao.projectstoreapi.domain.dto.ClientDTO;
import com.ccostao.projectstoreapi.domain.dto.ClientNewDTO;
import com.ccostao.projectstoreapi.domain.enums.ClientType;
import com.ccostao.projectstoreapi.repository.AddressRepository;
import com.ccostao.projectstoreapi.repository.ClientRepository;
import com.ccostao.projectstoreapi.service.exception.DataIntegrityException;
import com.ccostao.projectstoreapi.service.exception.ObjectNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public List<Client> findAll() {
		List<Client> client = clientRepository.findAll();
		return client;
	}
	
	public Client find(Integer id) {
		Optional<Client> client = clientRepository.findById(id);
		return client.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id + ", Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		obj = clientRepository.save(obj);
		addressRepository.saveAll(obj.getAdresses());
		return obj;
	}
	
	public Client update(Client obj) {
		Client newObj = find(obj.getId());
		updateData(newObj, obj);
		return clientRepository.save(newObj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clientRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageRequest);
	}

	public Client fromDTO(ClientDTO objDto) {
		return new Client(objDto.getId(), objDto.getName(), objDto.getEmail(), null, null);
	}

	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}
	
	public Client formDTO(ClientNewDTO objDTO) {
		Client cli = new Client(null, objDTO.getName(), objDTO.getEmail(), objDTO.getCpfOrCnpj(), ClientType.toEnum(objDTO.getType()));
		City city = new City(objDTO.getCityId(), null, null);
		Address addrs = new Address(null, objDTO.getStreet(), objDTO.getNumber(), objDTO.getComplement(), objDTO.getDistrict(), objDTO.getZipcode(), cli, city);
		cli.getAdresses().add(addrs);
		cli.getPhones().add(objDTO.getPhone1());
		if (objDTO.getPhone2() != null) {
			cli.getPhones().add(objDTO.getPhone2());
		}
		if (objDTO.getPhone3() != null) {
			cli.getPhones().add(objDTO.getPhone3());
		}
		return cli;
	}
}
