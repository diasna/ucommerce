package com.pengenkerjadirumah.persistence.service;

import com.pengenkerjadirumah.persistence.Images;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.pengenkerjadirumah.persistence.Product;

public interface ProductService {

    public Product addProduct(Product product);

    public Page<Product> getProducts(Pageable pageable, String keywords, long tenantId);

    public void deleteProduct(long id);
    
    public Images addImages(Images images);
    
}
