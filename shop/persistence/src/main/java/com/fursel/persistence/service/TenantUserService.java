package com.fursel.persistence.service;

import com.fursel.persistence.TenantUser;

public interface TenantUserService {
	
	public TenantUser findByEmail(String email);
	
	public boolean registerTenant(TenantUser tenant);
	
}
