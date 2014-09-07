package com.fursel.tenant.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fursel.persistence.service.TenantUserService;
import com.fursel.tenant.domain.RegisterTenant;

@Controller
@RequestMapping("/")
public class SiteController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);

	@Autowired
	private TenantUserService tenantUserService;

	@RequestMapping(method = RequestMethod.GET)
	public String landing(@RequestHeader(value="Host") String host) {
		LOG.info("Host: {}", host);
		return "/landing";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(){
		return "/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("tenant") RegisterTenant registerTenant, BindingResult result, RedirectAttributes redirectAttrs){
		if (result.hasErrors()) {
			return "/register";
		}
		tenantUserService.registerTenant(registerTenant.toEntity());
		redirectAttrs.addFlashAttribute("message", "Success Register !");
		return "redirect:/login";
	}
	
	@ModelAttribute("tenant")
	private RegisterTenant getTenant() {
		return new RegisterTenant();
	}
}
