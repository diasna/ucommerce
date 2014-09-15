package com.fursel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fursel.persistence.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT c FROM Product c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.category.tenant.id=?2",
            countQuery = "SELECT count(c) FROM Product c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.category.tenant.id=?2")
    Page<Product> findProducts(String keywords, long tenantId, Pageable pageable);
}
