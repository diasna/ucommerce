package com.pengenkerjadirumah.tenant.controller;

import javax.validation.Valid;

import com.pengenkerjadirumah.persistence.Tenant;
import com.pengenkerjadirumah.persistence.TenantUser;
import com.pengenkerjadirumah.persistence.service.TenantUserService;
import com.pengenkerjadirumah.tenant.domain.RegisterTenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class SiteController {

    private static final Logger LOG = LoggerFactory.getLogger(SiteController.class);
    
    @Autowired
    private TenantUserService tenantUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = RequestMethod.GET)
    public String landing() {
        return "redirect:/home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "/register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("tenant") RegisterTenant registerTenant, BindingResult result, RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {
            return "/register";
        }
        TenantUser tenantUser = registerTenant.toEntity();
        tenantUser.setPassword(passwordEncoder.encode(tenantUser.getPassword()));

        tenantUserService.registerTenant(tenantUser);
        redirectAttrs.addFlashAttribute("message", "Success Register !");
        return "redirect:/login";
    }

    @ModelAttribute("tenant")
    private RegisterTenant getTenant() {
        return new RegisterTenant();
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "/index";
    }
}
