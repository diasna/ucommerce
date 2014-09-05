package com.fursel.persistence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fursel.persistence.Customer;
import com.fursel.persistence.repository.CustomerRepository;
import com.fursel.persistence.security.Assembler;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private CustomerRepository dao;
	
	@Autowired
	private Assembler assembler;

	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    Customer userEntity = dao.findByEmail(username);
	    if (userEntity == null)
	      throw new UsernameNotFoundException("user not found");
	    return assembler.buildCustomerUser(userEntity);
	}

}
