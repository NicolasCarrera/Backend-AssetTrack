package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "maintenance_activities")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "maintenance_activities_seq")
    @SequenceGenerator(name = "maintenance_activities_seq", sequenceName = "maintenance_activities_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maintenance_report_id", nullable = false)
    @JsonIgnore
    private MaintenanceReport maintenanceReport;

    @Column(nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "activity_tasks", joinColumns = @JoinColumn(name = "activity_id"))
    @Column(name = "task_description")
    private Set<String> tasks;
}
