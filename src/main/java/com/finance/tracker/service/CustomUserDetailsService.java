package com.finance.tracker.service;

import com.finance.tracker.repository.UserRepository;
import com.finance.tracker.entity.User;
import com.finance.tracker.config.CustomUserDetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    private final UserRepository repo;
    public CustomUserDetailsService(UserRepository repo){
        this.repo = repo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user = repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with Email: "+email));

        return new CustomUserDetails(user);
    }
}   
