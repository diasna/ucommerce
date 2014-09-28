package com.fursel.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fursel.persistence.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT c FROM Category c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.tenant.id=?2",
            countQuery = "SELECT count(c) FROM Category c WHERE (c.name LIKE %?1% OR c.description LIKE %?1%) AND c.tenant.id=?2")
    Page<Category> findCategories(String keywords, long tenantId, Pageable pageable);

//    @Query(value = "SELECT c FROM Category c WHERE c.tenant.id=?1")
//    public List<Category> getAllByTenant(long tenantId);
}
