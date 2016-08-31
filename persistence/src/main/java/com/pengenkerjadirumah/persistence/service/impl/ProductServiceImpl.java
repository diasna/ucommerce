package com.pengenkerjadirumah.persistence.service.impl;

import com.pengenkerjadirumah.persistence.Images;
import com.pengenkerjadirumah.persistence.Product;
import com.pengenkerjadirumah.persistence.repository.ImagesRepository;
import com.pengenkerjadirumah.persistence.repository.ProductRepository;
import com.pengenkerjadirumah.persistence.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Page<Product> getProducts(Pageable pageable, String keywords, long tenantId) {
        return repository.findProducts(keywords, tenantId, pageable);
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
