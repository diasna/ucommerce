package com.pengenkerjadirumah.persistence.repository;

import com.pengenkerjadirumah.persistence.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT c FROM Product c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.category.tenant.id=?2",
            countQuery = "SELECT count(c) FROM Product c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.category.tenant.id=?2")
    Page<Product> findProducts(String keywords, long tenantId, Pageable pageable);
}
