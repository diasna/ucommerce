package com.pengenkerjadirumah.store.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.pengenkerjadirumah.persistence.Customer;
import com.pengenkerjadirumah.persistence.service.CustomerService;
import com.pengenkerjadirumah.store.domain.ProductForm;
import com.pengenkerjadirumah.store.domain.RegisterCustomer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class SiteController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String landing(@RequestHeader(value = "Host") String host, Model model) {

        return "/index";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("customer") RegisterCustomer customer,
                           BindingResult result, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {
            return "/register";
        }

        Customer customerUser = customer.toEntity();
        customerUser.setPassword(passwordEncoder.encode(customerUser.getPassword()));

        customerService.registerCustomer(customerUser);
        redirectAttrs.addFlashAttribute("message", "Success Register !");
        return "redirect:/login";
    }


}
