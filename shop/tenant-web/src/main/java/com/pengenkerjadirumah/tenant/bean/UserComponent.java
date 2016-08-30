package com.pengenkerjadirumah.tenant.bean;

import com.pengenkerjadirumah.persistence.TenantUser;
import com.pengenkerjadirumah.tenant.controller.CategoryController;
import com.pengenkerjadirumah.tenant.security.TenantUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by rin on 8/18/2016.
 */

@Component
@Scope(value="session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserComponent {

    private static final Logger LOG = LoggerFactory.getLogger(UserComponent.class);

    private TenantUserDetails user;

    public TenantUserDetails getUser() {
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getPrincipal() instanceof TenantUserDetails) {
                user = (TenantUserDetails) auth.getPrincipal();
            } else {
                user = null;
            }
        }
        return user;
    }

}
