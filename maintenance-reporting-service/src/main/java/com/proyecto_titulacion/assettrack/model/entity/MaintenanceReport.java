package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyecto_titulacion.assettrack.model.type.MaintenanceType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "maintenance_reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_reports_seq")
    @SequenceGenerator(name = "maintenance_reports_seq", sequenceName = "maintenance_reports_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "report_type", nullable = false)
    private MaintenanceType type;
    @Column(name = "report_date", nullable = false)
    private LocalDate date;

    @ElementCollection
    @CollectionTable(name = "maintenance_observations", joinColumns = @JoinColumn(name = "maintenance_report_id"))
    @Column(name = "observation_description")
    private List<String> observations;

    @ElementCollection
    @CollectionTable(name = "maintenance_recommendations", joinColumns = @JoinColumn(name = "maintenance_report_id"))
    @Column(name = "recommendation_description")
    private List<String> recommendations;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "asset_id", nullable = false)
    private Long assetId;
    @Column(name = "branch_id", nullable = false)
    private Long branchId;
    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @OneToMany(mappedBy = "maintenanceReport", cascade = CascadeType.ALL)
    //@JsonManagedReference
    private List<MaintenanceActivity> maintenanceActivities;

    @OneToOne(mappedBy = "maintenanceReport", cascade = CascadeType.ALL)
    //@JsonManagedReference
    private PreventiveMaintenance preventiveMaintenance;

    @OneToOne(mappedBy = "maintenanceReport", cascade = CascadeType.ALL)
    //@JsonManagedReference
    private CorrectiveMaintenance correctiveMaintenance;

    public void setMaintenanceActivities(List<MaintenanceActivity> maintenanceActivities) {
        this.maintenanceActivities = maintenanceActivities;
        if (maintenanceActivities != null && !maintenanceActivities.isEmpty()) {
            for (MaintenanceActivity maintenanceActivity : maintenanceActivities) {
                maintenanceActivity.setMaintenanceReport(this);
            }
        }
    }

    public void setPreventiveMaintenance(PreventiveMaintenance preventiveMaintenance) {
        this.preventiveMaintenance = preventiveMaintenance;
        if (preventiveMaintenance != null) {
            preventiveMaintenance.setMaintenanceReport(this);
        }
    }

    public void setCorrectiveMaintenance(CorrectiveMaintenance correctiveMaintenance) {
        this.correctiveMaintenance = correctiveMaintenance;
        if (correctiveMaintenance != null) {
            correctiveMaintenance.setMaintenanceReport(this);
        }
    }
}
