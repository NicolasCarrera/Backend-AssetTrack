package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "preventive_maintenance_seq")
    @SequenceGenerator(name = "preventive_maintenance_seq", sequenceName = "preventive_maintenance_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "maintenance_report_id", updatable = false, nullable = false)
    @JsonIgnore
    private MaintenanceReport maintenanceReport;

    private String frequency;
}
