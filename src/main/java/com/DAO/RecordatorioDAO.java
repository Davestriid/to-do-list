/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.models.Recordatorio;
import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Davestriid
 */
public class RecordatorioDAO {

    // CREAR un recordatorio
    public void guardarRecordatorio(Recordatorio recordatorio) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(recordatorio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // LEER todos los recordatorios
    public List<Recordatorio> obtenerTodosLosRecordatorios() {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Recordatorio r", Recordatorio.class).getResultList();
        } finally {
            em.close();
        }
    }

    // LEER recordatorios por tarea
    public List<Recordatorio> obtenerRecordatoriosPorTarea(int idTarea) {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Recordatorio r WHERE r.idTarea.idTarea = :id", Recordatorio.class)
                    .setParameter("id", idTarea)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // ACTUALIZAR un recordatorio
    public void actualizarRecordatorio(Recordatorio recordatorio) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(recordatorio);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ELIMINAR un recordatorio
    public void eliminarRecordatorio(Recordatorio recordatorio) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Recordatorio r = em.find(Recordatorio.class, recordatorio.getIdRecordatorio());
            if (r != null) {
                em.remove(r);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
