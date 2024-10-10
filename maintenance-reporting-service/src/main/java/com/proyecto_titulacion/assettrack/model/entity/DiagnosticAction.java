package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "diagnostic_actions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "corrective_maintenance_id", nullable = false)
    @JsonBackReference
    private CorrectiveMaintenance correctiveMaintenance;

    @Column(nullable = false)
    private String action;

    @ElementCollection
    @CollectionTable(name = "actions_description", joinColumns = @JoinColumn(name = "corrective_maintenance_id"))
    @Column(name = "description")
    private Set<String> descriptions;
}
