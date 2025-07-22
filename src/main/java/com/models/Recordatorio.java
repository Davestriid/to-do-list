/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Davestriid
 */
@Entity
@Table(catalog = "agenda_to_do", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recordatorio.findAll", query = "SELECT r FROM Recordatorio r"),
    @NamedQuery(name = "Recordatorio.findByIdRecordatorio", query = "SELECT r FROM Recordatorio r WHERE r.idRecordatorio = :idRecordatorio"),
    @NamedQuery(name = "Recordatorio.findByFechaHora", query = "SELECT r FROM Recordatorio r WHERE r.fechaHora = :fechaHora")})
public class Recordatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_recordatorio", nullable = false)
    private Integer idRecordatorio;
    @Basic(optional = false)
    @Column(name = "fecha_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;
    @JoinColumn(name = "id_tarea", referencedColumnName = "id_tarea", nullable = false)
    @ManyToOne(optional = false)
    private Tarea idTarea;

    public Recordatorio() {
    }

    public Recordatorio(Integer idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public Recordatorio(Integer idRecordatorio, Date fechaHora) {
        this.idRecordatorio = idRecordatorio;
        this.fechaHora = fechaHora;
    }

    public Integer getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(Integer idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Tarea getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Tarea idTarea) {
        this.idTarea = idTarea;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecordatorio != null ? idRecordatorio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recordatorio)) {
            return false;
        }
        Recordatorio other = (Recordatorio) object;
        if ((this.idRecordatorio == null && other.idRecordatorio != null) || (this.idRecordatorio != null && !this.idRecordatorio.equals(other.idRecordatorio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.models.Recordatorio[ idRecordatorio=" + idRecordatorio + " ]";
    }
    
}
