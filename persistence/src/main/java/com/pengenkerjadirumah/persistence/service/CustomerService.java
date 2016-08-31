package com.pengenkerjadirumah.persistence.service;

import com.pengenkerjadirumah.persistence.Customer;

public interface CustomerService {

    public Customer findByEmail(String email, Long tenantId);

    public boolean registerCustomer(Customer customer);
}
