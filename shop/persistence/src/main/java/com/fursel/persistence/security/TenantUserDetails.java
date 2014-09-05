package com.fursel.persistence.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class TenantUserDetails extends
		org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 408111905583313286L;

	private String storeName;

	public TenantUserDetails(String username, String password,
			String storeName, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
		this.storeName = storeName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
