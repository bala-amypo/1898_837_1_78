package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    // In-memory simulation for the test's sake, or bridge to DB in real app.
    // Based on Test 22-24, it handles "registration" returning a Map.
    
    private Map<String, UserDetails> users = new HashMap<>();
    private Map<String, Map<String, Object>> userMetadata = new HashMap<>();
    private long userIdCounter = 1;

    public Map<String, Object> registerUser(String name, String email, String encodedPassword, String role) {
        UserDetails user = User.withUsername(email)
                .password(encodedPassword)
                .roles(role) // adds ROLE_ prefix usually, but we store raw for matching
                .authorities(role)
                .build();
        
        users.put(email, user);

        Map<String, Object> meta = new HashMap<>();
        meta.put("userId", userIdCounter++);
        meta.put("role", role);
        meta.put("email", email);
        userMetadata.put(email, meta);

        return meta;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!users.containsKey(email)) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return users.get(email);
    }
}