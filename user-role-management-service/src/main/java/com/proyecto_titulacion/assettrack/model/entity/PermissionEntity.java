package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissions_seq")
    @SequenceGenerator(name = "permissions_seq", sequenceName = "permissions_seq", allocationSize = 1)
    private Long id;

    @Column(name = "permission_name", unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<RoleEntity> roles = new HashSet<>();
}
