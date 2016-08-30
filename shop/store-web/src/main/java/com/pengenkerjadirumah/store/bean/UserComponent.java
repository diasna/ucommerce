package com.pengenkerjadirumah.store.bean;

import com.pengenkerjadirumah.store.security.CustomerUserDetails;
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

    private CustomerUserDetails user;

    public CustomerUserDetails getUser() {
        if (user == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth.getPrincipal() instanceof CustomerUserDetails) {
                user = (CustomerUserDetails) auth.getPrincipal();
            } else {
                user = null;
            }
        }
        return user;
    }

}
