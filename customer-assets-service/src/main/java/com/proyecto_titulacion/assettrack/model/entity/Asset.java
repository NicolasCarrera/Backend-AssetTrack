package com.proyecto_titulacion.assettrack.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.proyecto_titulacion.assettrack.model.type.StatusType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "assets")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "image_path", columnDefinition = "TEXT")
    private String imagePath;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(nullable = false)
    private String brand;
    @Column(nullable = false)
    private String model;
    @Column(name = "serial_number", unique = true)
    private String serial;
    @Column(name = "acquisition_date")
    private LocalDate acquisition;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType status;

    @Column(name = "company_id", nullable = false)
    private Long companyId;
    @Column(name = "branch_id", nullable = false)
    private Long branchId;

    @ElementCollection
    @CollectionTable(name = "asset_files", joinColumns = @JoinColumn(name = "asset_id"))
    @Column(name = "file_path", columnDefinition = "TEXT")
    private List<String> files;
}
