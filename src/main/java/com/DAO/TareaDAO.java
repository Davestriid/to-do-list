package com.DAO;

import com.models.Tarea;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Davestriid
 */
public class TareaDAO {

    // para crear una nueva tarea
    public void guardarTarea(Tarea tarea) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tarea);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // para poder ver todas las tareas
    public List<Tarea> obtenerTodasLasTareas() {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Tarea t", Tarea.class).getResultList();
        } finally {
            em.close();
        }
    }

    //para actualizar las tareas existentes
    public void actualizarTarea(Tarea tarea) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(tarea); // Reemplaza por versión actualizada
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    //para eliminar las tareas
    public void eliminarTarea(Tarea tarea) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Tarea tareaBorrar = em.find(Tarea.class, tarea.getIdTarea());
            if (tareaBorrar != null) {
                em.remove(tareaBorrar);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // para buscar una tarea por su id
    public Tarea obtenerTareaPorId(int id) {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }

    //para listar las tareas
    public List<Tarea> listarTareas() {
        EntityManager em = DbManager.getEntityManager();
        List<Tarea> tareas = em.createQuery("SELECT t FROM Tarea t", Tarea.class).getResultList();
        em.close();
        return tareas;
    }

    //para obtener las tareas por su titulo
    public List<Tarea> obtenerNombreTareaPorTitulo(String titulo) {
        EntityManager em = DbManager.getEntityManager();
        List<Tarea> tareas = em.createQuery(
                "SELECT t FROM Tarea t WHERE t.titulo LIKE :titulo", Tarea.class)
                .setParameter("titulo", "%" + titulo + "%")
                .getResultList();
        em.close();
        return tareas;
    }

    //para buscar tareas por su id
    public Tarea buscarPorId(Integer id) {
        EntityManager em = DbManager.getEntityManager();
        Tarea tarea = em.find(Tarea.class, id);
        em.close();
        return tarea;
    }
}
