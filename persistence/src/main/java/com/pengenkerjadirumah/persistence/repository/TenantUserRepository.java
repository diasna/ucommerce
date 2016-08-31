package com.pengenkerjadirumah.persistence.repository;

import com.pengenkerjadirumah.persistence.TenantUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TenantUserRepository extends CrudRepository<TenantUser, Long> {

    @Query("select c from TenantUser c where c.email = :email")
    TenantUser findByEmail(@Param("email") String email);
}
