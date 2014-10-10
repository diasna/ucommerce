package com.fursel.persistence.service;

import com.fursel.persistence.Customer;

public interface CustomerService {

    public Customer findByEmail(String email, Long tenantId);

    public boolean registerCustomer(Customer customer);
}
