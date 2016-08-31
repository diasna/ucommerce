package com.pengenkerjadirumah.persistence.repository;

import org.springframework.data.jpa.repository.Query;

import com.pengenkerjadirumah.persistence.Tenant;
import org.springframework.data.repository.CrudRepository;

public interface TenantRepository extends CrudRepository<Tenant, Long> {
	
	@Query(value="SELECT t FROM Tenant t WHERE t.name = ?1")
	Tenant findByDomain(String domain);
	
	@Query(value="SELECT t FROM Tenant t WHERE t.domainId = ?1")
	Tenant findByDomainId(String domain);
	
}
