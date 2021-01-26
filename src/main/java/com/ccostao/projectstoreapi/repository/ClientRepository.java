package com.ccostao.projectstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccostao.projectstoreapi.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{

}
