package com.zavyalov.kiselev.coursework.features.user;

import com.zavyalov.kiselev.coursework.model.UserEntity;
import com.zavyalov.kiselev.coursework.features.user.repository.UserRepository;
import com.zavyalov.kiselev.coursework.features.user.converter.UserConverter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository,
                                    UserConverter userConverter) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity entity = userRepository.findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found"));
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new User(entity.getLogin(), entity.getPassword(), authorities);
    }
}
