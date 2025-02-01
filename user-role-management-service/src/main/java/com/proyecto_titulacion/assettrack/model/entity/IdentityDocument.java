package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.proyecto_titulacion.assettrack.model.type.DocumentType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "identity_document")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdentityDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "identity_document_seq")
    @SequenceGenerator(name = "identity_document_seq", sequenceName = "identity_document_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType document;
    @Column(unique = true, nullable = false)
    private String identification;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserEntity user;
}
