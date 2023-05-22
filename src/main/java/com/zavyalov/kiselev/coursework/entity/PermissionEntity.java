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
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    private Long permissionId;

    @Column(name = "permission_name", nullable = false, unique = true)
    private String permissionName;

    @ManyToMany
    private Set<RoleEntity> roleset;
}
