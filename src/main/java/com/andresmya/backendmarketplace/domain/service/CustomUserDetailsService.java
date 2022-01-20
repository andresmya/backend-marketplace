package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.Privilege;
import com.andresmya.backendmarketplace.domain.Role;
import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByEmail(username).orElseThrow(() -> new BadCredentialsException(""));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getAuthorities(user.getRole())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        return getGrantedAuthorities(getPrivileges(role));
    }

    private List<String> getPrivileges(Role role) {
        List<String> privileges = new ArrayList<>();
        Collection<Privilege> collection = role.getPrivileges();
        for (Privilege privilege : collection) {
            privileges.add(privilege.getName());
        }
        privileges.add("ROLE_" + role.getName());
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
