package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyecto_titulacion.assettrack.model.type.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_seq")
    @SequenceGenerator(name = "companies_seq", sequenceName = "companies_seq", allocationSize = 1)
    private Long id;

    private String name;

    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Branch> branches;
}
