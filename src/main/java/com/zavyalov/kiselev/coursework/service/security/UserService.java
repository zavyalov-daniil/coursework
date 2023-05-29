package com.zavyalov.kiselev.coursework.service.security;

import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.form.RegisterForm;
import com.zavyalov.kiselev.coursework.repository.UserRepository;
import com.zavyalov.kiselev.coursework.view.UserView;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(UserConverter userConverter, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public UserView registerNewUser(RegisterForm registerForm) {
        registerForm.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        UserEntity entity = userRepository.save(userConverter.registerDtoToEntity(registerForm));
        return userConverter.entityToView(entity);
    }
}
