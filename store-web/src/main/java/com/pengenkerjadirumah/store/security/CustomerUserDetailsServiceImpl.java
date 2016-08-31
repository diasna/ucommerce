package com.pengenkerjadirumah.store.security;

import javax.servlet.http.HttpServletRequest;

import com.pengenkerjadirumah.persistence.Customer;
import com.pengenkerjadirumah.persistence.repository.CustomerUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerUserDetailsServiceImpl.class);
	
    @Autowired
    private CustomerUserRepository dao;
    
    @Autowired
    private Assembler assembler;

    @Autowired
    private HttpServletRequest request;
    
    @Autowired
    private CustomerUserRepository customerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	String host = request.getHeader("Host");
    	LOG.info("Loading user for host: "+ host);
    	Customer customer = null;
    	if(host.endsWith(".fursel.com")){
            customer = customerRepository.findByTenantName(username, host.substring(0, host.indexOf(".")));
    		LOG.info("Loading tenant data :"+customer.toString());
    	} else {
            customer = customerRepository.findByTenantDomainId(username, host);
    		LOG.info("Loading tenant data :"+customer.toString());
    	}
    	if (customer == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return assembler.buildCustomerUser(customer);
    }
}
