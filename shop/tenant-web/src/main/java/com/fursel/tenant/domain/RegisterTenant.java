package com.fursel.tenant.domain;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fursel.persistence.Tenant;
import com.fursel.util.validator.FieldMatch;
import com.fursel.util.validator.FieldMatchList;

@FieldMatchList({
    @FieldMatch(first = "password", second = "cpassword", message = "The password fields must match"),
})
public class RegisterTenant {
	
	private String id;
	
	@NotNull
	@NotEmpty
	@Email
	private String email;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotNull
	@NotEmpty
	private String cpassword;
	
	@NotNull
	@NotEmpty
	private String storeName;
	
	private boolean active;

	public RegisterTenant() {

	}

	public RegisterTenant(String id, String email, String password, String cpassword, String storeName,
			boolean active) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.cpassword = cpassword;
		this.storeName = storeName;
		this.active = active;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	
	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public com.fursel.persistence.TenantUser toEntity(){
		com.fursel.persistence.TenantUser tenantUser = new com.fursel.persistence.TenantUser();
		tenantUser.setActive(true);
		tenantUser.setEmail(this.getEmail());
		tenantUser.setPassword(this.getPassword());
		Tenant tenant = new Tenant();
		tenant.setName(this.getStoreName());
		tenantUser.setTenant(tenant);
		return tenantUser;
	}
}
