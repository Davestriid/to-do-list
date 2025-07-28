/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.models.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Davestriid
 */
public class UsuarioDAO {

    //Ver Usuario
    public List<Usuario> VerUsuarios() {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    //Crear Usuario
    public void createUsuarioAdmin(String nombre, String email, String telefono, String tipoDeRol, String contraseña) {

        EntityManager em = DbManager.getEntityManager();
        em.getTransaction().begin();
        Usuario u = new Usuario();

        u.setNombre(nombre);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setContrasena(contraseña);
        u.setRol(tipoDeRol);
        
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }
    
    
    public void createUsuario(String nombre, String email, String telefono, String contraseña) {
    createUsuarioAdmin(nombre, email, telefono, "usuario", contraseña);
    }

    //Edita Usuario
    public String actualizarUsuario(Usuario Usuario) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(Usuario);
            em.getTransaction().commit();
            return "Usuario actualizada correctamente";
        } catch (Exception e) {
            em.getTransaction().rollback();
            return "Error al actualizar la Usuario";
        } finally {
            em.close();
        }
    }

    //Eliminar Usuario
    
    public void eliminarUsuario(Usuario usuario) {
        EntityManager em = DbManager.getEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuarioEliminar = em.find(Usuario.class, usuario.getIdUsuario());
            if (usuarioEliminar != null) {
                em.remove(usuarioEliminar);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }


    //Buscar un usuario por su nombre
    public Usuario buscarUsuarioPorNombre(String nombreUsuario) throws Exception {
        EntityManager em = DbManager.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarUsuarioPorNombre", Usuario.class);
            query.setParameter("nombreUsuario", nombreUsuario);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (Exception e) {
            throw new Exception("Error al buscar usuario por nombre: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    //Obtener todos los usuarios para llenar los combobox
    public List<Usuario> buscarTodosLosUsuarios() throws Exception {
        EntityManager em = DbManager.getEntityManager();
        try {
            TypedQuery<Usuario> query = em.createNamedQuery("Usuario.buscarTodosLosUsuarios", Usuario.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new Exception("Error al obtener todos los usuarios: " + e.getMessage(), e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    // para buscar un usuario por su id
    public Usuario obtenerUsuarioPorId(int id) {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

//    public List<Usuario> listarUsuarios() {
//        EntityManager em = DbManager.getEntityManager();
//        try {
//            TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
//            return query.getResultList();
//        } finally {
//            em.close();
//        }
//    }
    
    public List<Usuario> listarUsuarios() {
        EntityManager em = DbManager.getEntityManager();
        List<Usuario> tareas = em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        em.close();
        return tareas;
    }
    
    public List<Usuario> obtenerTodosLosUsuarios() {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }

    //buscar usuario por nombre
    public Usuario buscarPorNombre(String nombre) {
        EntityManager em = DbManager.getEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class)
                    .setParameter("nombre", nombre)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
