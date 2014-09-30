/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fursel.persistence.service.impl;

import com.fursel.persistence.Order;
import com.fursel.persistence.repository.OrderRepository;
import com.fursel.persistence.security.TenantUserDetails;
import com.fursel.persistence.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public Page<Order> getOrders(Pageable pageable, String keywords) {
        TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return orderRepository.findOrders(keywords, userDetails.getTenantId(), pageable);
    }
    
}
