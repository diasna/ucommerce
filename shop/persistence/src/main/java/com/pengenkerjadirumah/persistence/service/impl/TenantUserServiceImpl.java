package com.pengenkerjadirumah.persistence.service.impl;

import javax.annotation.Resource;

import com.pengenkerjadirumah.persistence.Tenant;
import com.pengenkerjadirumah.persistence.TenantUser;
import com.pengenkerjadirumah.persistence.repository.TenantRepository;
import com.pengenkerjadirumah.persistence.repository.TenantUserRepository;
import com.pengenkerjadirumah.persistence.service.TenantUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantUserServiceImpl implements TenantUserService {

    @Resource
    private TenantUserRepository repository;
    
    @Resource
    private TenantRepository tenantRepository;

    @Override
    public TenantUser findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean registerTenant(TenantUser tenant) {
        tenantRepository.save(tenant.getTenant());
        return repository.save(tenant) != null ? true : false;
    }

	@Override
	public Tenant findByDomain(String domain) {
		return tenantRepository.findByDomain(domain);
	}

	@Override
	public Tenant findByDomainId(String domain) {
		return tenantRepository.findByDomainId(domain);
	}
}
