package com.fursel.persistence.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fursel.persistence.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(value="select c from Category c where c.name LIKE '%:name%' or c.description LIKE '%:description%'", countQuery="select count(c) from Category c where c.name LIKE '%:name%' or c.description LIKE '%:description%'")
	Page<Category> findCategories(Pageable pageable, @Param("name") String name, @Param("description") String description);
	
}
