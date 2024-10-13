package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.proyecto_titulacion.assettrack.model.type.StatusType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "branches")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "branches_seq")
    @SequenceGenerator(name = "branches_seq", sequenceName = "branches_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String location;
    private String email;
    private String phone;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer assets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    @JsonBackReference
    private Company company;
}
