/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.*;
import javax.ejb.Local;

/**
 * Interfaz para el componente GestionProductos
 * @author ismpere
 * @author vicrojo
 */
@Local
public interface BeanGestionProductosLocal {

    /**
     * Devuelve una lista de vinos por su categoría y denominación de origen
     * Si no encuentra ningun vino con esas características devuelve una lista vacía
     * @param categoria categoría de los vinos
     * @param denOrigen denominación de origen de los vinos
     * @return una lista con los vinos que cumplen esas dos características
     */
    java.util.List<Vino> getVinos(String categoria, String denOrigen);

    /**
     * Devuelve las referencias por el identificador de un vino
     * Si no hay ninguna referencia, devuelve una lista vacía
     * @param vinoId identificador del vino
     * @return una lista con las referencias de ese vino
     */
    java.util.List<Referencia> getReferencias(int vinoId);

    /**
     * Devuelve una lista de preferencias por el login de un usuario
     * @param login identificador del usuario
     * @return una lista de preferencias del usuario que tiene como login el introducido
     */
    java.util.List<Preferencia> getPrefrencias(String login);
    
    /**
     * Método de prueba para comprobar el componente local
     * @return mensaje de prueba "hola"
     */
    String hola();

}
