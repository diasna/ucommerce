package com.fursel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fursel.persistence.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
