package com.fursel.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fursel.persistence.Category;
import java.util.List;

public interface CategoryService {

    public boolean addCategory(Category category);

    public Page<Category> getCategories(Pageable pageable, String keywords);

    public List<Category> getAllByTenant();

    public void deleteCategory(long id);
}
