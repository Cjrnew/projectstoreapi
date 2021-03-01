package com.ccostao.projectstoreapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ccostao.projectstoreapi.domain.Category;
import com.ccostao.projectstoreapi.domain.dto.CategoryDTO;
import com.ccostao.projectstoreapi.repository.CategoryRepository;
import com.ccostao.projectstoreapi.service.exception.DataIntegrityException;
import com.ccostao.projectstoreapi.service.exception.ObjectNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll() {
		return categoryRepository.findAll();
	}
	
	public Category find(Integer id) {
		Optional<Category> category = categoryRepository.findById(id);
		return category.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id + ", Type: " + Category.class.getName()));
	}
	
	public Category insert(Category category) {
		category.setId(null);
		return categoryRepository.save(category);
	}

	public Category update(Category obj) {
		Category newObj = find(obj.getId());
		updateData(newObj, obj);
		return categoryRepository.save(newObj);
	}
	
	private void updateData(Category newObj, Category obj) {
		newObj.setName(obj.getName());
	}

	public void delete(Integer id) {
		find(id);
		try {
			categoryRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("You cannot delete a category that has products.");
		}
	}

	public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoryRepository.findAll(pageRequest);
	}
	
	public Category fromDTO(CategoryDTO categoryDto) {
		return new Category(categoryDto.getId(), categoryDto.getName());
	}
	
}
