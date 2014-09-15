package com.fursel.persistence.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fursel.persistence.Product;

public interface ProductService {

    public boolean addProduct(Product product);

    public Page<Product> getProducts(Pageable pageable, String keywords);

    public void deleteProduct(long id);
}
