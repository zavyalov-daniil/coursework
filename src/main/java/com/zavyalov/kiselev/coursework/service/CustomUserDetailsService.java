package com.zavyalov.kiselev.coursework.service;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new CustomUserDetails(entity);
    }
}
