package com.ccostao.projectstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccostao.projectstoreapi.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>{

}
