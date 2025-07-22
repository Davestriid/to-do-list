/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.util.Date;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Davestriid
 */
@XmlType(name = "ResumenTareas")
public class ResumenTareas {

    private String titulo;
    private String descripcion;
    private String estado;
    private Date fechaVencimiento;
    private String nombreCategoria;
    private String nombrePrioridad;

    public ResumenTareas() {

    }

    public ResumenTareas(String titulo, String descripcion, String estado, Date fechaVencimiento, String nombreCategoria, String nombrePrioridad) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaVencimiento = fechaVencimiento;
        this.nombreCategoria = nombreCategoria;
        this.nombrePrioridad = nombrePrioridad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombrePrioridad() {
        return nombrePrioridad;
    }

    public void setNombrePrioridad(String nombrePrioridad) {
        this.nombrePrioridad = nombrePrioridad;
    }
    @Override
    public String toString() {
        return "ResumenTareas{" +
               "titulo='" + titulo + '\'' +
               ", descripcion='" + descripcion + '\'' +
               ", estado='" + estado + '\'' +
               ", fechaVencimiento=" + fechaVencimiento +
               ", nombreCategoria='" + nombreCategoria + '\'' +
               ", nombrePrioridad='" + nombrePrioridad + '\'' +
               '}';
    }
}