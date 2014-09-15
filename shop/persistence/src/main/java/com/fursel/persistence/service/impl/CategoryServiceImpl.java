package com.fursel.persistence.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fursel.persistence.Category;
import com.fursel.persistence.Tenant;
import com.fursel.persistence.repository.CategoryRepository;
import com.fursel.persistence.security.TenantUserDetails;
import com.fursel.persistence.service.CategoryService;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);
    @Autowired
    private CategoryRepository repository;

    @Override
    @Transactional(readOnly = false)
    public boolean addCategory(Category category) {
        TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        category.setTenant(new Tenant(userDetails.getTenantId()));
        return repository.save(category) != null ? true : false;
    }

    @Override
    public Page<Category> getCategories(Pageable pageable, String keywords) {
        TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findCategories(keywords, userDetails.getTenantId(), pageable);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteCategory(long id) {
        repository.delete(id);
    }

    @Override
    public List<Category> getAllByTenant() {
        TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.getAllByTenant(userDetails.getTenantId());
    }
}
