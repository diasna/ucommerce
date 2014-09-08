package com.fursel.tenant.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fursel.persistence.Category;

public class CategoryForm {

	@NotNull
	@NotEmpty
	private String name;

	private String description;
	
	public CategoryForm() {

	}

	public CategoryForm(String name, String description, String tenant) {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category toEntity() {
		Category category = new Category();
		category.setName(this.getName());
		category.setDescription(this.getDescription());
		return category;
	}

}
