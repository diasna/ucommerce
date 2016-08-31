package com.pengenkerjadirumah.persistence.repository;

import com.pengenkerjadirumah.persistence.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query(value = "SELECT c FROM Category c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.tenant.id=?2",
            countQuery = "SELECT count(c) FROM Category c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.tenant.id=?2")
    Page<Category> findCategories(String keywords, long tenantId, Pageable pageable);

}
