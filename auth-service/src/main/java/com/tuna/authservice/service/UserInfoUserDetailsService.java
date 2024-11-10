package com.tuna.authservice.service;

import com.tuna.authservice.model.data.AuthUserDetails;
import com.tuna.authservice.model.entity.AuthUser;
import com.tuna.authservice.repository.AuthServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthServiceRepository authServiceRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AuthUser> authUser = authServiceRepository.findByUserName(username);

        return authUser.map(AuthUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));
    }
}
