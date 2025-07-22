package com.main;

import com.view.InicioDeSesion;
//import com.servicio.ServicioTareas;
//import jakarta.xml.ws.Endpoint;

public class ToDoListMain {
    
    public static void main(String[] args) {       
        InicioDeSesion inicioSes = new InicioDeSesion();
        inicioSes.setVisible(true);
    
//        Endpoint.publish("http://localhost:8080/ServicioTareas", new ServicioTareas());
//        System.out.println("Servicio Publicado en http://localhost:8080/ServicioTareas?wsdl");
    }
}