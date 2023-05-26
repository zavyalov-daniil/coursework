package com.zavyalov.kiselev.coursework.service.security;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.form.RegisterDto;
import com.zavyalov.kiselev.coursework.repository.UserRepository;
import com.zavyalov.kiselev.coursework.view.UserView;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UserRepository userRepository,
                                    UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        return new CustomUserDetails(entity);
    }
}
