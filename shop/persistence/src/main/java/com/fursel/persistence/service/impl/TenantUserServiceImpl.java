package com.fursel.persistence.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fursel.persistence.TenantUser;
import com.fursel.persistence.repository.TenantRepository;
import com.fursel.persistence.repository.TenantUserRepository;
import com.fursel.persistence.service.TenantUserService;

@Service
public class TenantUserServiceImpl implements TenantUserService {
	
	@Resource
	private TenantUserRepository repository;
	
	@Resource
	private TenantRepository tenantRepository;
	
	@Autowired 
	private PasswordEncoder encoder;
	
	@Override
	public TenantUser findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean registerTenant(TenantUser tenant) {
		tenant.setPassword(encoder.encode(tenant.getPassword()));
		tenantRepository.save(tenant.getTenant());
		return repository.save(tenant) != null? true : false;
	}

}
