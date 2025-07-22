/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Davestriid
 */
@Entity
@Table(catalog = "agenda_to_do", schema = "public", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre_prioridad"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prioridad.buscarTodasLasPrioridades", query = "SELECT p FROM Prioridad p"),
    @NamedQuery(name = "Prioridad.findByIdPrioridad", query = "SELECT p FROM Prioridad p WHERE p.idPrioridad = :idPrioridad"),
    @NamedQuery(name = "Prioridad.buscarPrioridadPorNombre", query = "SELECT p FROM Prioridad p WHERE p.nombrePrioridad = :nombrePrioridad")})
public class Prioridad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_prioridad", nullable = false)
    private Integer idPrioridad;
    @Basic(optional = false)
    @Column(name = "nombre_prioridad", nullable = false, length = 50)
    private String nombrePrioridad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPrioridad")
    private List<Tarea> tareaList;

    public Prioridad() {
    }

    public Prioridad(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public Prioridad(Integer idPrioridad, String nombrePrioridad) {
        this.idPrioridad = idPrioridad;
        this.nombrePrioridad = nombrePrioridad;
    }

    public Integer getIdPrioridad() {
        return idPrioridad;
    }

    public void setIdPrioridad(Integer idPrioridad) {
        this.idPrioridad = idPrioridad;
    }

    public String getNombrePrioridad() {
        return nombrePrioridad;
    }

    public void setNombrePrioridad(String nombrePrioridad) {
        this.nombrePrioridad = nombrePrioridad;
    }

    @XmlTransient
    public List<Tarea> getTareaList() {
        return tareaList;
    }

    public void setTareaList(List<Tarea> tareaList) {
        this.tareaList = tareaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrioridad != null ? idPrioridad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prioridad)) {
            return false;
        }
        Prioridad other = (Prioridad) object;
        if ((this.idPrioridad == null && other.idPrioridad != null) || (this.idPrioridad != null && !this.idPrioridad.equals(other.idPrioridad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.Prioridad[ idPrioridad=" + idPrioridad + " ]";
    }
    
}
