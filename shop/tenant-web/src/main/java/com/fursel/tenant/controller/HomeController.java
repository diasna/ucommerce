package com.fursel.tenant.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(method = RequestMethod.GET)
	public String landing(@RequestHeader(value="Host") String host) {
		LOG.info("Host: {}", host);
		return "/home";
	}
	
}
