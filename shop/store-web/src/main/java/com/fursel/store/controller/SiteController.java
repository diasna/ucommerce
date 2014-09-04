package com.fursel.store.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fursel.persistence.service.CustomerService;
import com.fursel.store.domain.Customer;
import com.fursel.store.domain.Item;

@Controller
@RequestMapping("/")
public class SiteController {
	
	@Autowired
	private CustomerService customerService;

	private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String getCurrentMenu(@RequestHeader(value="Host") String host, Model model) {
		LOG.info("Loading Items {}", host);
		model.addAttribute("items",getMenuItems());
		return "/home";
	}

	private List<Item> getMenuItems() {
		List<Item> items = new ArrayList<Item>();
		items.add(new Item("1", "Xperia Z1", new BigDecimal(6250000)));
		items.add(new Item("2", "Galaxy S5", new BigDecimal(25000)));
		items.add(new Item("3", "Iphone 5S", new BigDecimal(500000)));
		return items;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(){
		return "/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("customer") Customer customer, BindingResult result, RedirectAttributes redirectAttrs){
		if (result.hasErrors()) {
			return "/register";
		}
		customerService.registerCustomer(customer.toEntity());
		redirectAttrs.addFlashAttribute("message", "Success Register !");
		return "redirect:/login";
	}
	
	@ModelAttribute("customer")
	private Customer getCustomer() {
		return new Customer();
	}
}
