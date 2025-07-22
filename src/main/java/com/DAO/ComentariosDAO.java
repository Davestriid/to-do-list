/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.models.Comentarios;
import javax.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Davestriid
 */

public class ComentariosDAO {


    // para crear un comentario
    public void guardarComentario(Comentarios comentario) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(comentario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // para cer todos lo comentarios
    public List<Comentarios> obtenerTodosLosComentarios() {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Comentarios c", Comentarios.class).getResultList();
        } finally {
            em.close();
        }
    }

    // para ver los comentarios por las tareas
    public List<Comentarios> obtenerComentariosPorTarea(int idTarea) {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Comentarios c WHERE c.idTarea.idTarea = :id", Comentarios.class)
                    .setParameter("id", idTarea)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    // para actualizar un comentario
    public void actualizarComentario(Comentarios comentario) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(comentario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //para eliminar un comentario
    public void eliminarComentario(Comentarios comentario) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Comentarios comentarioBorrar = em.find(Comentarios.class, comentario.getIdComentario());
            if (comentarioBorrar != null) {
                em.remove(comentarioBorrar);
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
