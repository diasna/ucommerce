/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pengenkerjadirumah.tenant.controller;

import com.pengenkerjadirumah.persistence.Order;
import com.pengenkerjadirumah.persistence.service.OrderService;
import com.pengenkerjadirumah.tenant.domain.OrderForm;
import com.pengenkerjadirumah.tenant.domain.PageWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Dias Nurul Arifin <dias@nsiapay.net>
 */
@Controller
@RequestMapping("/orders")
public class OrderController extends AbstractController{
    
    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired
    private OrderService orderService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String landing(Model uiModel, Pageable pageable, @ModelAttribute("order") OrderForm orderForm) {
        PageWrapper<Order> page = new PageWrapper<Order>(orderService.getOrders(pageable, orderForm.getQuery(), getUser().getTenantId()), "/orders");
        uiModel.addAttribute("page", page);
        return "/order";
    }
}
