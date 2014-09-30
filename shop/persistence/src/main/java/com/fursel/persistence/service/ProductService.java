package com.fursel.persistence.service;

import com.fursel.persistence.Images;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fursel.persistence.Product;

public interface ProductService {

    public Product addProduct(Product product);

    public Page<Product> getProducts(Pageable pageable, String keywords);

    public void deleteProduct(long id);
    
    public Images addImages(Images images);
    
}
