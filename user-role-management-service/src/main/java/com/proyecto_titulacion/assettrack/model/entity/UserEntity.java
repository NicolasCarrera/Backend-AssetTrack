package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyecto_titulacion.assettrack.model.type.StatusType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true)
    @Email(message = "Correo electrónico inválido")
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String phone;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private IdentityDocument identityDocuments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    @JsonManagedReference
    private RoleEntity role;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;

    public void setIdentityDocuments(IdentityDocument identityDocuments) {
        this.identityDocuments = identityDocuments;
        if(identityDocuments != null) {
            identityDocuments.setUser(this);
        }
    }
}
