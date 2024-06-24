package com.proyecto_titulacion.assettrack.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "sucursal")
public class Sucursal {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "numeroEmpleados")
    private Long numeroEmpleados;

    @Column(name = "numeroActivos")
    private Long numeroActivos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public void setNumeroEmpleados(Long numeroEmpleados) {
        this.numeroEmpleados = numeroEmpleados;
    }

    public Long getNumeroActivos() {
        return numeroActivos;
    }

    public void setNumeroActivos(Long numeroActivos) {
        this.numeroActivos = numeroActivos;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
