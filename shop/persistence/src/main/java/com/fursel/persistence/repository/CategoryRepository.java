package com.fursel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fursel.persistence.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(value="select c from Category c where c.name LIKE %?1% or c.description LIKE %?1%", countQuery="select count(c) from Category c where c.name LIKE %?1% or c.description LIKE %?1%")
	Page<Category> findCategories(String keywords, Pageable pageable);
	
}
