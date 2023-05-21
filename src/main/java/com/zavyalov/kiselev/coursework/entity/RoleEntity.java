package com.zavyalov.kiselev.coursework.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class RoleEntity {
    @Id
    private Long id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roleset")
    private Set<PermissionEntity> permissionsSet;
}
