package com.fursel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fursel.persistence.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
}
