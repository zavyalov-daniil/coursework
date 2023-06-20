package com.zavyalov.kiselev.coursework.service.security;

import com.zavyalov.kiselev.coursework.entity.RabbitMqMessage;
import com.zavyalov.kiselev.coursework.entity.RoleEntity;
import com.zavyalov.kiselev.coursework.entity.UserEntity;
import com.zavyalov.kiselev.coursework.form.RegisterForm;
import com.zavyalov.kiselev.coursework.repository.RabbitMqRepository;
import com.zavyalov.kiselev.coursework.repository.RoleRepository;
import com.zavyalov.kiselev.coursework.repository.UserRepository;
import com.zavyalov.kiselev.coursework.view.UserView;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private UserConverter userConverter;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private RabbitMqRepository rabbitMqRepository;

    @Value("${coursework.rabbitmq.default-from-email}")
    private String defaultFromEmail;

    public UserService(UserConverter userConverter,
                       PasswordEncoder passwordEncoder,
                       UserRepository userRepository,
                       RoleRepository roleRepository,
                       RabbitMqRepository rabbitMqRepository) {
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.rabbitMqRepository = rabbitMqRepository;
    }

    public UserView registerNewUser(RegisterForm registerForm) {
        registerForm.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        UserEntity entity = userRepository.save(userConverter.registerDtoToEntity(registerForm));
        rabbitMqRepository.sendMessage(new RabbitMqMessage(
                defaultFromEmail,
                entity.getLogin(),
                "Регистрация Coursework",
                "Поздравляем, вы успешно зарегистрированы. Ваш id: " + entity.getId()));
        return userConverter.entityToView(entity);
    }

    public void setRole(int userId, Long roleId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<RoleEntity> role = roleRepository.findById(roleId);

        if (user.isPresent() && role.isPresent()) {
            user.get().setRole(role.get());
            role.get().getUserEntities().add(user.get());

            userRepository.save(user.get());
            roleRepository.save(role.get());
//            roleRepository.sa
        }

    }

    public List<UserView> getAll() {
        List<UserEntity> usersEnt = userRepository.findAll();
        List<UserView> usersView = new ArrayList<>(usersEnt.size());
        for (UserEntity ent : usersEnt) {
            usersView.add(userConverter.entityToView(ent));
        }
        return usersView;
    }
}
