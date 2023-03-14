package com.ruanazevedo.fullstackprojectbackend.dto;

import java.io.Serializable;

import com.ruanazevedo.fullstackprojectbackend.domain.Category;

public class CategoryDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	
	public CategoryDTO() {}

	public CategoryDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CategoryDTO(Category category) {
		setId(category.getId());
		setName(category.getName());
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
