package com.fursel.persistence.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class TenantUserDetails extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 408111905583313286L;
    private long tenantId;
    private String storeName;

    public TenantUserDetails(String username, String password,
            String storeName, long tenantId, boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired,
                credentialsNonExpired, accountNonLocked, authorities);
        this.tenantId = tenantId;
        this.storeName = storeName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public long getTenantId() {
        return tenantId;
    }

    public void setTenantId(long tenantId) {
        this.tenantId = tenantId;
    }
}
