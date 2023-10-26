package com.zavyalov.kiselev.coursework.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "login", nullable = false, unique = true)
    @NotBlank(message = "Login must be not blank")
    @Size(min = 4, max = 50)
    private String login;

    @Column(name = "password")
    private String password;

    public UserEntity(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @Nullable
    private RoleEntity role;
}
