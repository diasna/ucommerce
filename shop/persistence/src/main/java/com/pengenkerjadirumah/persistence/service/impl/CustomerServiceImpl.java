package com.pengenkerjadirumah.persistence.service.impl;

import javax.annotation.Resource;

import com.pengenkerjadirumah.persistence.Customer;
import com.pengenkerjadirumah.persistence.repository.CustomerUserRepository;
import com.pengenkerjadirumah.persistence.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerUserRepository repository;


    @Transactional
    public Customer findByEmail(String email, Long tenantId) {
        return repository.findByEmail(email, tenantId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean registerCustomer(Customer customer) {
        return repository.save(customer) != null ? true : false;
    }
}
