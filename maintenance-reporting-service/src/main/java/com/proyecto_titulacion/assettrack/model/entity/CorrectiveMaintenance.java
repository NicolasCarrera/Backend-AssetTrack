package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "corrective_maintenance")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CorrectiveMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "corrective_maintenance_seq")
    @SequenceGenerator(name = "corrective_maintenance_seq", sequenceName = "corrective_maintenance_seq", allocationSize = 1)
    private Long id;

    @OneToOne
    @JoinColumn(name = "maintenance_report_id", updatable = false, nullable = false)
    @JsonBackReference
    private MaintenanceReport maintenanceReport;
    @Column(name = "issue_description", columnDefinition = "TEXT")
    private String issueDescription;
    @Column(name = "failure_cause")
    private String failureCause;
    @Column(name = "post_maintenance_status")
    private String postMaintenanceStatus;

    @OneToMany(mappedBy = "correctiveMaintenance", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DiagnosticAction> diagnosticActions;
}
