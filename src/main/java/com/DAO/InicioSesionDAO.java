/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.models.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Davestriid
 */
public class InicioSesionDAO {
    EntityManager em;

    public Usuario autenticarUsuario(String nombre, String contrasena) {
        try {
            this.em = DbManager.getEntityManager();
            
            // 1. Buscar al usuario por su nombre
            TypedQuery<Usuario> query = this.em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
            query.setParameter("nombre", nombre);
            
            Usuario usuarioEncontrado = query.getSingleResult();
            
            // 2. Verificar la contraseña
            // ¡ADVERTENCIA DE SEGURIDAD! Usa hashing.
            if (contrasena.equals(usuarioEncontrado.getContrasena())) {
                return usuarioEncontrado; // Autenticación exitosa
            } else {
                return null; // Contraseña incorrecta
            }
            
        } catch (NoResultException e) {
            // El nombre no se encontró en la base de datos
            return null;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public boolean esAdministrador(String nombreUsuario) {
        try {
            this.em = DbManager.getEntityManager();

            TypedQuery<String> query = this.em.createQuery(
                "SELECT u.rol FROM Usuario u WHERE u.nombre = :nombre", String.class);
            query.setParameter("nombre", nombreUsuario);

            String rol = query.getSingleResult();

            return rol != null && rol.equalsIgnoreCase("administrador");

        } catch (NoResultException e) {
            return false; // Usuario no encontrado
        } catch (Exception e) {
            System.err.println("Error al verificar rol: " + e.getMessage());
            return false;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
