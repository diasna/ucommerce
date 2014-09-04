package com.fursel.persistence.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fursel.persistence.Customer;
import com.fursel.persistence.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Resource
	private CustomerRepository repository;
	
	@Autowired 
	private PasswordEncoder encoder;
	
	@Transactional
	public Customer findByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean registerCustomer(Customer customer) {
		customer.setPassword(encoder.encode(customer.getPassword()));
		return repository.save(customer) != null? true : false;
	}

}
