package com.fursel.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fursel.persistence.Category;

public interface CategoryService {
	
	public boolean addCategory(Category category);
	
	public Page<Category> getCategories(Pageable pageable, String name, String description);
	
}
