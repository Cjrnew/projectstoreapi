package com.ccostao.projectstoreapi.domain.dto;

import java.io.Serializable;

import com.ccostao.projectstoreapi.domain.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getname();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}