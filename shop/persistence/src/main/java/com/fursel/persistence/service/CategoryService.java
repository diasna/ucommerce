package com.fursel.persistence.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fursel.persistence.Category;
import com.fursel.persistence.json.CategoryJson;

public interface CategoryService {

    public boolean addCategory(Category category);

    public Page<Category> getCategories(Pageable pageable, String keywords);

    public List<CategoryJson> getAllByTenant();

    public void deleteCategory(long id);
}
