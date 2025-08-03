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
    public String actualizarCategoria(Categoria categoria) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(categoria); // Asegúrate que el objeto es gestionado
            em.getTransaction().commit();
            return "Categoría actualizada correctamente";
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "Error al actualizar la categoría: " + e.getMessage(); // Mejora para ver el detalle
        } finally {
            em.close();
        }
    }

    
    //Eliminar Categoria
    public void eliminarCategoria(Categoria categoria) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Categoria gestionada = em.merge(categoria);
            em.remove(gestionada);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar la categoría", e);
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
            List<Categoria> categorias = em.createQuery("SELECT c FROM Categoria c", Categoria.class).getResultList();
            System.out.println("CATEGORÍAS ENCONTRADAS: " + categorias.size());
            return categorias;
        } finally {
            em.close();
        }
    }

    
    public Categoria obtenerCategoriaPorId(int id_categoria) {
        EntityManager em = DbManager.getEntityManager();
        Categoria categoria = null;

        try {
            categoria = em.find(Categoria.class, id_categoria);
        } catch (Exception e) {
            System.err.println("Error al buscar la categoría por ID: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        return categoria;
    }
}
