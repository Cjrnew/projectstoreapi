package com.ccostao.projectstoreapi.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ccostao.projectstoreapi.domain.Category;

public class CategoryDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="Required field.")
	@Length(min= 5, max=50, message="The length must be between 5 and 50 characters.")
	private String name;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
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
