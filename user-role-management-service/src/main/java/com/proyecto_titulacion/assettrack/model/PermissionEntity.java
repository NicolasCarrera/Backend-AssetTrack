package com.proyecto_titulacion.assettrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "permission_name", unique = true, nullable = false)
    private String permissionName;

}
