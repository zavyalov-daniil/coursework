package com.zavyalov.kiselev.coursework.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {
    @Id
    private Long roleId;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<PermissionEntity> permissionsSet;

    @OneToMany(cascade = CascadeType.REMOVE)
    @Nullable
    private Set<UserEntity> userEntities;
}
