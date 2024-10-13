package com.proyecto_titulacion.assettrack.model.entity;

import com.proyecto_titulacion.assettrack.model.type.MaintenanceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "work_orders")
public class WorkOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_orders_seq")
    @SequenceGenerator(name = "work_orders_seq", sequenceName = "work_orders_seq", allocationSize = 1)
    private Long id;

    @Column(name = "maintenance_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaintenanceType type;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "asset_id", nullable = false)
    private Long assetId;
    @Column(name = "branch_id", nullable = false)
    private Long branchId;
    @Column(name = "company_id", nullable = false)
    private Long companyId;
    @Column(name = "scheduled_date", nullable = false)
    private LocalDate date;

    @Column(name = "issue_description", columnDefinition = "TEXT")
    private String issueDescription;

    @ElementCollection
    @CollectionTable(name = "work_order_tasks", joinColumns = @JoinColumn(name = "work_order_id"))
    @Column(name = "task")
    private List<String> tasks;

    @Transient
    private String state;
}
