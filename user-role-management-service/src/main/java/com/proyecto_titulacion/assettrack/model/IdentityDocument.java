package com.proyecto_titulacion.assettrack.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "identity_document")
public class IdentityDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;
    @Column(name = "identification")
    private String identification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
