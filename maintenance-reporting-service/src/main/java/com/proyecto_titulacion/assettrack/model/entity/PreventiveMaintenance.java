package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "preventive_maintenance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PreventiveMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "maintenance_report_id", updatable = false, nullable = false)
    @JsonBackReference
    private MaintenanceReport maintenanceReport;

    private String frequency;
}
