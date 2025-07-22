/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Davestriid
 */
public class DbManager {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.mycompany_Proyecto_to_do_list_jar_1.0-SNAPSHOTPU");
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}



