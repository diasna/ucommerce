package com.fursel.persistence.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fursel.persistence.Customer;
import com.fursel.persistence.TenantUser;

@Service("assembler")
public class Assembler {

	@Transactional(readOnly = true)
	public User buildCustomerUser(Customer userEntity) {

		String username = userEntity.getEmail();
		String password = userEntity.getPassword();
		boolean enabled = userEntity.isActive();
		boolean accountNonExpired = userEntity.isActive();
		boolean credentialsNonExpired = userEntity.isActive();
		boolean accountNonLocked = userEntity.isActive();

		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));

		User user = new User(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
	
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

		TenantUserDetails user = new TenantUserDetails(username, password, storeName , tenantId, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return user;
	}
}