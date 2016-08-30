package com.pengenkerjadirumah.persistence.service;

import com.pengenkerjadirumah.persistence.Tenant;
import com.pengenkerjadirumah.persistence.TenantUser;

public interface TenantUserService {

    public TenantUser findByEmail(String email);

    public boolean registerTenant(TenantUser tenant);
    
    public Tenant findByDomain(String domain);
    
    public Tenant findByDomainId(String domain);
    
}
