package com.pengenkerjadirumah.tenant.security;

import com.pengenkerjadirumah.persistence.TenantUser;
import com.pengenkerjadirumah.persistence.repository.TenantUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TenantUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private TenantUserRepository tenantUserRepository;

    @Autowired
    private Assembler assembler;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TenantUser userEntity = tenantUserRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return assembler.buildTenantUser(userEntity);
    }
}
