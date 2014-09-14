package com.fursel.persistence.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fursel.persistence.Product;
import com.fursel.persistence.repository.ProductRepository;
import com.fursel.persistence.security.TenantUserDetails;
import com.fursel.persistence.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository repository;
	
	@Override
	@Transactional(readOnly = false)
	public boolean addProduct(Product product) {
		return repository.save(product) != null ? true : false;
	}

	@Override
	public Page<Product> getProdcuts(Pageable pageable, String keywords) {
		TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		LOG.info("LOADING DATA FOR :"+userDetails.getTenantId());
		return repository.findProducts(keywords, userDetails.getTenantId(), pageable);
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteProduct(long id) {
		repository.delete(id);
	}

}
