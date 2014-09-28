package com.fursel.tenant.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fursel.persistence.Category;

public class CategoryForm {

    private Long id;
    @NotNull
    @NotEmpty
    private String name;
    private String description;
    private Long parentId;
    private String query = "";

    public CategoryForm() {
    }

    public CategoryForm(String name, String description, String tenant) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Category toEntity() {
        Category category = new Category();
        category.setName(this.getName());
        category.setDescription(this.getDescription());
		if (this.getParentId() != null) {
        	category.setParent(new Category(this.getParentId()));
        }
        return category;
    }
}
