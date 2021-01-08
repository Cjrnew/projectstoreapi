package com.ccostao.projectstoreapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ccostao.projectstoreapi.domain.Category;
import com.ccostao.projectstoreapi.service.CategoryService;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> categoryList() {
		List<Category> category = categoryService.findAll();
		return ResponseEntity.ok().body(category);
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Category category = categoryService.find(id);
		return ResponseEntity.ok().body(category);
	}
}
