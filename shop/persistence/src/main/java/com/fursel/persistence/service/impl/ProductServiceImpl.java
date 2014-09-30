package com.fursel.persistence.service.impl;

import com.fursel.persistence.Images;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fursel.persistence.Product;
import com.fursel.persistence.repository.ImagesRepository;
import com.fursel.persistence.repository.ProductRepository;
import com.fursel.persistence.security.TenantUserDetails;
import com.fursel.persistence.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);
    
    @Autowired
    private ProductRepository repository;

    @Autowired
    private ImagesRepository imgRepository;
    
    @Override
    @Transactional(readOnly = false)
    public Product addProduct(Product product) {
        Product p = repository.save(product);
        return p;
    }

    @Override
    public Page<Product> getProducts(Pageable pageable, String keywords) {
        TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findProducts(keywords, userDetails.getTenantId(), pageable);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProduct(long id) {
        repository.delete(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Images addImages(Images images) {
        Images i = imgRepository.save(images);
        return i;
    }
}
