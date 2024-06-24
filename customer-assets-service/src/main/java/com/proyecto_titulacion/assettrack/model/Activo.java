package com.proyecto_titulacion.assettrack.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "users")
public class Activo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "marca", nullable = false)
    private String marca;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "empresa", nullable = false)
    private String empresa;

    @Column(name = "sucursal", nullable = false)
    private String sucursal;

    @Column(name = "area")
    private String area;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaAdquisicion" , nullable = false)
    private Date fechaAdquisicion;

    @Column(name = "serial", nullable = false)
    private String serial;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaUltimoMantenimiento" , nullable = false)
    private Date fechaUltimoMantenimiento;

    @Temporal(TemporalType.DATE)
    @Column(name = "fechaProximoMantenimiento" , nullable = false)
    private Date fechaProximoMantenimiento;

    @Column(name = "empresaMantenimiento" , nullable = false)
    private String empresaMantenimiento;

    @Column(name = "proveedor" , nullable = false)
    private String proveedor;


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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Date getFechaUltimoMantenimiento() {
        return fechaUltimoMantenimiento;
    }

    public void setFechaUltimoMantenimiento(Date fechaUltimoMantenimiento) {
        this.fechaUltimoMantenimiento = fechaUltimoMantenimiento;
    }

    public Date getFechaProximoMantenimiento() {
        return fechaProximoMantenimiento;
    }

    public void setFechaProximoMantenimiento(Date fechaProximoMantenimiento) {
        this.fechaProximoMantenimiento = fechaProximoMantenimiento;
    }

    public String getEmpresaMantenimiento() {
        return empresaMantenimiento;
    }

    public void setEmpresaMantenimiento(String empresaMantenimiento) {
        this.empresaMantenimiento = empresaMantenimiento;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }
}
