package com.ccostao.projectstoreapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ccostao.projectstoreapi.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
