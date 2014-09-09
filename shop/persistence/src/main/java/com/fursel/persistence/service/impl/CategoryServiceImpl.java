package com.fursel.persistence.service.impl;

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

@Service
public class CategoryServiceImpl implements CategoryService {

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
		return repository.findCategories(keywords, pageable);
	}

}
