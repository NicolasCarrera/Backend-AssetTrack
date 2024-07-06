package com.proyecto_titulacion.assettrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "reporte")
public class Reporte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "empresa_id", nullable = false)
    private Long empresaId;

    @Column(name = "sucursal_id", nullable = false)
    private Long sucursalId;

    @Column(name = "activo_id", nullable = false)
    private Long activoId;

    @Column(name = "tipo_mantenimiento", nullable = false)
    @Enumerated(EnumType.STRING)
    private Mantenimiento tipoMantenimiento;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_mantenimiento ", nullable = false)
    private Date fecha;

    @OneToOne(mappedBy = "reporte", cascade = CascadeType.ALL)
    private ReportePreventivo preventivo;

    @OneToOne(mappedBy = "reporte", cascade = CascadeType.ALL)
    private ReporteCorrectivo correctivo;
}
