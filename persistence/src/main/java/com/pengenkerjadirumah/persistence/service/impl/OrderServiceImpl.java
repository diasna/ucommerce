/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengenkerjadirumah.persistence.service.impl;

import com.pengenkerjadirumah.persistence.Order;
import com.pengenkerjadirumah.persistence.repository.OrderRepository;
import com.pengenkerjadirumah.persistence.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Override
    public Page<Order> getOrders(Pageable pageable, String keywords, long tenantId) {
        return orderRepository.findOrders(keywords, tenantId, pageable);
    }
    
}
