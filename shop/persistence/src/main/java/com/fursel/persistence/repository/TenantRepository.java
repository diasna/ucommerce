package com.fursel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fursel.persistence.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
	
	@Query(value="SELECT t FROM Tenant t WHERE t.name = ?1")
	Tenant findByDomain(String domain);
	
	@Query(value="SELECT t FROM Tenant t WHERE t.domainId = ?1")
	Tenant findByDomainId(String domain);
	
}
