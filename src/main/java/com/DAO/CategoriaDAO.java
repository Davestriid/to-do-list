/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.models.Categoria;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Davestriid
 */
public class CategoriaDAO {

    //Ver Categorias   
    public List<Categoria> VerCategorias() {
        EntityManager em = DbManager.getEntityManager();
        try{
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        }finally{
            em.close();
        }
    }
    
    //Crear Categorias
    public void createCategoria(String nombreCategoria) {
        EntityManager em = DbManager.getEntityManager();
        em.getTransaction().begin();
        Categoria c = new Categoria();
        
        c.setNombreCategoria(nombreCategoria);
        
        em.persist(c);
        em.getTransaction().commit();
        em.close();
    }
    
    //Edita Categorias
    public String actualizarCategoria(Categoria Categoria){
        EntityManager em = DbManager.getEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(Categoria);
            em.getTransaction().commit();
            return "Categoria actualizada correctamente";
        }catch(Exception e){
            em.getTransaction().rollback();
            return "Error al actualizar la Categoria";
        }finally{
        em.close();
        }
    }
    
    //Eliminar Categoria
    public void eliminarCategoria(Long id) {
        EntityManager em = DbManager.getEntityManager();
        try {
            Categoria tar = em.find(Categoria.class, id);
            if (tar != null) {
                em.getTransaction().begin();
                em.remove(tar);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
    
    //Buscar una categoria por su nombre
    public Categoria buscarCategoriaPorNombre(String nombreCategoria) throws Exception {
        EntityManager em = DbManager.getEntityManager();
        try {
            TypedQuery<Categoria> query = em.createNamedQuery("Categoria.buscarCategoriaPorNombre", Categoria.class);
            query.setParameter("nombreCategoria", nombreCategoria);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; 
        } catch (Exception e) {
            throw new Exception("Error al buscar categoría por nombre: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    //Obtener todas las categorias para llenar los combobox
    public List<Categoria> buscarTodasLasCategorias() throws Exception {
        EntityManager em = DbManager.getEntityManager();
        try {
            TypedQuery<Categoria> query = em.createNamedQuery("Categoria.buscarTodasLasCategorias", Categoria.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception("Error al obtener todas las categorías: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    //buscar categoria por nombre
    public Categoria buscarPorNombre(String nombre) {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c WHERE c.nombreCategoria = :nombre", Categoria.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    
    
    //obtener todas las categorias
    public List<Categoria> obtenerTodasLasCategorias() {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
        } finally {
            em.close();
        }
    }
}