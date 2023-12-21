package org.ecommerce.casestudy.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private final String firstName;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean accountEnabled, String firstName) {
        super(username, password, accountEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }
}