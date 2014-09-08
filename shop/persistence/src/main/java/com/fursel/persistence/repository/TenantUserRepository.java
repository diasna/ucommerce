package com.fursel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fursel.persistence.TenantUser;

public interface TenantUserRepository extends JpaRepository<TenantUser, Long> {

	@Query("select c from TenantUser c where c.email = :email")
	public TenantUser findByEmail(@Param("email") String email);
	
}
