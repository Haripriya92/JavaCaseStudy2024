package org.ecommerce.casestudy.security;

import lombok.extern.slf4j.Slf4j;
import org.ecommerce.casestudy.database.dao.UserDao;
import org.ecommerce.casestudy.database.dao.UserRoleDao;
import org.ecommerce.casestudy.database.entity.User;
import org.ecommerce.casestudy.database.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Inside user details service");
        User user = userDao.findByEmailIgnoreCase(username);

        if (user == null) {
            throw new UsernameNotFoundException("Username '" + username + "' not found in the database");
        }

        boolean accountIsEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        List<UserRole> userRoles = userRoleDao.findByUserId(user.getId());

        // Setup user roles
        Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(userRoles);

        // Return an instance of CustomUserDetails
        return new CustomUserDetails(user.getEmail(), user.getPassword(), springRoles, accountNonExpired, accountNonLocked, credentialsNonExpired, accountIsEnabled, user.getFirstName());
    }
    public static Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getUserRole().toString()));
        }

        // always add the user role
        authorities.add(new SimpleGrantedAuthority("USER"));

        return authorities;
    }
}
