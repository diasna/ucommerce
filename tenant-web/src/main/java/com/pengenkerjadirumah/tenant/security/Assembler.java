package com.pengenkerjadirumah.tenant.security;

import java.util.ArrayList;
import java.util.Collection;

import com.pengenkerjadirumah.persistence.TenantUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Assembler {

    @Transactional(readOnly = true)
    public TenantUserDetails buildTenantUser(TenantUser tenantUser) {

        String username = tenantUser.getEmail();
        String password = tenantUser.getPassword();
        String storeName = tenantUser.getTenant().getName();
        long tenantId = tenantUser.getTenant().getId();
        boolean enabled = tenantUser.isActive();
        boolean accountNonExpired = tenantUser.isActive();
        boolean credentialsNonExpired = tenantUser.isActive();
        boolean accountNonLocked = tenantUser.isActive();

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        TenantUserDetails user = new TenantUserDetails(username, password, storeName, tenantId, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        return user;
    }
}