package com.pengenkerjadirumah.persistence.repository;

import com.pengenkerjadirumah.persistence.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CustomerUserRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c where c.email = :email and c.tenant.id = :tenantId")
    Customer findByEmail(@Param("email") String email, @Param("tenantId") Long tenantId);

    @Query("SELECT c FROM Customer c WHERE c.tenant.name = :name AND c.email = :email")
    Customer findByTenantName(@Param("email") String email, @Param("name") String tenantName);

    @Query("SELECT c FROM Customer c WHERE c.tenant.domainId = :domain AND c.email = :email")
    Customer findByTenantDomainId(@Param("email") String email, @Param("domain") String domain);
}
