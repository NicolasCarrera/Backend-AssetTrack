package com.proyecto_titulacion.assettrack.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orden_trabajo")
public class OrdenTrabajo {
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
    @Column(name = "fecha_programada", nullable = false)
    private Date fechaProgramada;

    @Column(name = "estado_orden")
    private String estadoOrden;

    @ElementCollection
    @Column(name = "tareas")
    private List<String> tareas;
}
