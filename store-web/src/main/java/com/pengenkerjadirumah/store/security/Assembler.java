package com.pengenkerjadirumah.store.security;

import java.util.ArrayList;
import java.util.Collection;

import com.pengenkerjadirumah.persistence.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}