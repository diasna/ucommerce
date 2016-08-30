package com.pengenkerjadirumah.persistence.service;

import java.util.List;

import com.pengenkerjadirumah.persistence.json.CategoryJson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pengenkerjadirumah.persistence.Category;

public interface CategoryService {

    public boolean addCategory(Category category, long tenantId);

    public Page<Category> getCategories(Pageable pageable, String keywords, long tenantId);

    public List<CategoryJson> getAllByTenant(long tenantId);

    public void deleteCategory(long id);
}
