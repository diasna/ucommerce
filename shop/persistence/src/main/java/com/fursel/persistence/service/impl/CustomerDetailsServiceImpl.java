package com.fursel.persistence.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fursel.persistence.Customer;
import com.fursel.persistence.Tenant;
import com.fursel.persistence.repository.CustomerRepository;
import com.fursel.persistence.repository.TenantRepository;
import com.fursel.persistence.security.Assembler;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerDetailsServiceImpl.class);
	
    @Autowired
    private CustomerRepository dao;
    
    @Autowired
    private Assembler assembler;

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private TenantRepository tenantRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	String host = request.getHeader("Host");
    	LOG.info("Loading user for host: "+ host);
    	Tenant tenant = null;
    	if(host.endsWith(".fursel.com")){
    		tenant = tenantRepository.findByDomain(host.substring(0, host.indexOf(".")));
    		LOG.info("Loading tenant data :"+tenant.toString());
    	} else {
    		tenant = tenantRepository.findByDomainId(host);
    		LOG.info("Loading tenant data :"+tenant.toString());
    	}
    	Customer userEntity = dao.findByEmail(username, tenant.getId());
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return assembler.buildCustomerUser(userEntity);
    }
}
