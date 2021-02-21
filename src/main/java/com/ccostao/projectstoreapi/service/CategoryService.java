package com.ccostao.projectstoreapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccostao.projectstoreapi.domain.Category;
import com.ccostao.projectstoreapi.repository.CategoryRepository;
import com.ccostao.projectstoreapi.service.exception.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		List<Category> category = categoryRepository.findAll();
		return category;
	}
	
	public Category find(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id + ", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}

	public Category update(Category category) {
		find(category.getId());
		return categoryRepository.save(category);
	}
	
}
