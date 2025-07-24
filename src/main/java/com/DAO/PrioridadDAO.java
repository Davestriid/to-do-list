/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.models.Prioridad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Davestriid
 */
public class PrioridadDAO {
    
    //Ver Prioridads   
    public List<Prioridad> VerPrioridads() {
        EntityManager em = DbManager.getEntityManager();
        try{
            return em.createQuery("SELECT p FROM Prioridad p", Prioridad.class).getResultList();
        }finally{
            em.close();
        }
    }
    
    //Crear Prioridads
    public void createPrioridad(String nombrePrioridad) {
        EntityManager em = DbManager.getEntityManager();
        em.getTransaction().begin();
        Prioridad p = new Prioridad();
        
        p.setNombrePrioridad(nombrePrioridad);
        
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }
    
    //Editar Prioridads
    public String actualizarPrioridad(Prioridad Prioridad){
        EntityManager em = DbManager.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(Prioridad);
            em.getTransaction().commit();
            return "Prioridad actualizada correctamente";
        }catch(Exception e){
            em.getTransaction().rollback();
            return "Error al actualizar la Prioridad";
        }finally{
        em.close();
        }
    }
    
    //Eliminar Prioridad
    public void eliminarPrioridad(Long id) {
        EntityManager em = DbManager.getEntityManager();
        try {
            Prioridad priori = em.find(Prioridad.class, id);
            if (priori != null) {
                em.getTransaction().begin();
                em.remove(priori);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
    
    // Para buscar una Prioridad por su nombre
    public Prioridad buscarPrioridadPorNombre(String nombrePrioridad) throws Exception {
        EntityManager em = DbManager.getEntityManager();
        try {
            TypedQuery<Prioridad> query = em.createNamedQuery("Prioridad.buscarPrioridadPorNombre", Prioridad.class);
            query.setParameter("nombrePrioridad", nombrePrioridad);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } catch (Exception e) {
            throw new Exception("Error al buscar prioridad por nombre: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }    
    
    // Buscar todas las prioridades
    public List<Prioridad> buscarTodasLasPrioridades() throws Exception {
        EntityManager em = DbManager.getEntityManager();
        try {
            TypedQuery<Prioridad> query = em.createNamedQuery("Prioridad.buscarTodasLasPrioridades", Prioridad.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception("Error al obtener todas las prioridades: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    
    
    //buscar prioridad por nombre
    public Prioridad buscarPorNombre(String nombre) {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Prioridad p WHERE p.nombrePrioridad = :nombre", Prioridad.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
