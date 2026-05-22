package com.ws101.arce.ecommerceapi.service;

import com.ws101.arce.ecommerceapi.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomUserDetailsService implements UserDetailsService { //

    private final UserRepository userRepository;

    // Constructor injection for repository instances
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //
        // Natively uses findById() which matches up directly with your String primary key
        return userRepository.findById(username)
            .orElseThrow(() -> new UsernameNotFoundException("User registration entry not found: " + username)); //
    }
}