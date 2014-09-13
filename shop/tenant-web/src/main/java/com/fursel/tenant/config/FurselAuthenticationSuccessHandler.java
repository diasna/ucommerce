package com.fursel.tenant.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.context.request.RequestContextHolder;

import com.fursel.persistence.security.TenantUserDetails;

public class FurselAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	
	private static final Logger LOG = LoggerFactory.getLogger(FurselAuthenticationSuccessHandler.class);
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication auth) 
			throws IOException, ServletException {
		TenantUserDetails userDetails = (TenantUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String storeName = userDetails.getStoreName();
		String userName = userDetails.getUsername();
		String role = userDetails.getAuthorities().toString();
		
		LOG.info("Sign in User {} in {} with role {}", userName, storeName, role);
        redirectStrategy.sendRedirect(req, res, "http://"+storeName+".fursel.com/admin/home;jsessionid="+RequestContextHolder.currentRequestAttributes().getSessionId());
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}
