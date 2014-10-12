package com.fursel.persistence.service;

import com.fursel.persistence.Tenant;
import com.fursel.persistence.TenantUser;

public interface TenantUserService {

    public TenantUser findByEmail(String email);

    public boolean registerTenant(TenantUser tenant);
    
    public Tenant findByDomain(String domain);
    
    public Tenant findByDomainId(String domain);
    
}
