/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.servicio;

//import java.math.BigInteger;

import com.DAO.TareaDAO;
import com.models.ResumenTareas;
import com.models.Tarea;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davestriid
 */

@WebService
public class ServicioTareas {
    
     // Método para obtener todas las tareas
    @WebMethod
    public List<ResumenTareas> obtenerTodasLasTareas() {
        TareaDAO tardao = new TareaDAO();
        List<Tarea> tareasDesdeDB = tardao.listarTareas(); 
        
        List<ResumenTareas> resumenTareasList = new ArrayList<>();
        
        for (Tarea tarea : tareasDesdeDB) {
            ResumenTareas resumen = new ResumenTareas(
                tarea.getTitulo(),
                tarea.getDescripcion(),
                tarea.getEstado(),
                tarea.getFechaVencimiento() != null ? new java.sql.Date(tarea.getFechaVencimiento().getTime()) : null,
                tarea.getIdCategoria() != null ? tarea.getIdCategoria().getNombreCategoria(): null,
                tarea.getIdPrioridad() != null ? tarea.getIdPrioridad().getNombrePrioridad(): null
            );
            resumenTareasList.add(resumen);
        }
        return resumenTareasList;
    }
    
    
    
     @WebMethod
    public List<ResumenTareas> obtenerTareasPorTitulo(String titulo) {
        TareaDAO tardao = new TareaDAO();
        List<Tarea> tareasEncontradas = tardao.obtenerNombreTareaPorTitulo(titulo); 
        
        List<ResumenTareas> resumenTareasList = new ArrayList<>();

        if (tareasEncontradas == null || tareasEncontradas.isEmpty()){
            return resumenTareasList;
        }
        
        for (Tarea tarea : tareasEncontradas) {
             ResumenTareas resumen = new ResumenTareas(
                tarea.getTitulo(),
                tarea.getDescripcion(),
                tarea.getEstado(),
                tarea.getFechaVencimiento() != null ? new java.sql.Date(tarea.getFechaVencimiento().getTime()) : null,
                tarea.getIdCategoria() != null ? tarea.getIdCategoria().getNombreCategoria(): null,
                tarea.getIdPrioridad() != null ? tarea.getIdPrioridad().getNombrePrioridad(): null
            );
            resumenTareasList.add(resumen);
        }
        
        return resumenTareasList;
    }


     // Método para obtener una tarea por ID
    @WebMethod
    public ResumenTareas obtenerResumenTareas(Integer idTarea) { 
        TareaDAO tardao = new TareaDAO();
        
        Tarea tarea = tardao.buscarPorId(idTarea); 

        if (tarea == null) {
            return null; 
        }

        ResumenTareas resumen = new ResumenTareas(
            tarea.getTitulo(),
            tarea.getDescripcion(),
            tarea.getEstado(),
            tarea.getFechaVencimiento() != null ? new java.sql.Date(tarea.getFechaVencimiento().getTime()) : null, 
            tarea.getIdCategoria() != null ? tarea.getIdCategoria().getNombreCategoria(): null,
            tarea.getIdPrioridad() != null ? tarea.getIdPrioridad().getNombrePrioridad(): null 
        );
        return resumen;
    }
}
