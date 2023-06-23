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

    @Column(name = "handler_name", nullable = false)
    private String handlerName;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<RoleEntity> roleset;

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != PermissionEntity.class) {
            return false;
        } else {
            if (
                    (((PermissionEntity) o).getPermissionId() == this.permissionId) &&
                            (((PermissionEntity) o).getPermissionName().equals(permissionName))) {
                return true;
            } else return false;
        }
    }
}
