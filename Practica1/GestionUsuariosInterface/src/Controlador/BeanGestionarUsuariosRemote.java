/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.ejb.Remote;

/**
 * Interfaz para el componente GestionarUsuarios
 * @author ismpere
 * @author vicrojo
 */
@Remote
public interface BeanGestionarUsuariosRemote {

    /**
     * Comprueba si existe un abonado mediante su login
     * Devuelve true si es un abonado y false si no lo es
     * @param login el identificador a comprobar
     * @return un valor booleano de si es o no un abonado
     */
    boolean isAbonado(String login);

    /**
     * Comprueba si existe un empleado por login
     * Devuelve true si es un empleado y false si no lo es
     * @param login el identificador a comprobar
     * @return un valor booleano de si es o no un empleado
     */
    boolean isEmpleado(String login);

    /**
     * Comprueba si una contraseña es correcta con el login y el tipo de usuario
     * Devuelve true si la contraseña es correcta y false si no lo es
     * @param login el identificador del usuario
     * @param passwd la contraseña del usuario
     * @param tipoUser el tipo de usuario
     * @return un valor booleano de si es o no correcta la contraseña
     */
    boolean isPsswdOK(String login, String passwd, String tipoUser);

    /**
     * Retorna el Nif de un usuario por su login
     * Si el login no pertenece a ningun usuario, devuelve null
     * @param login el identificador del usuario
     * @return el Nif del usuario
     */
    String getNif(String login);

    /**
     * Añade un nuevo abonado con todos sus datos
     * Devuelve true si el abonado de añadido correctamente y false si no se ha añadido
     * @param nif Nif del abonado
     * @param nombre nombre del abonado
     * @param apellidos apellidos del abonado
     * @param login identificador del abonado
     * @param passwd contraseña del abonado
     * @return un valor booleano que representa si se ha añadido o no el abonado
     */
    boolean addAbonado(String nif, String nombre, String apellidos, String login, String passwd);

    /**
     * Elimina un abonado por su Nif
     * Devuelve true si el abonado de ha eliminado y false si no se ha eliminado
     * @param nif Nif del abonado
     * @return un valor booleano de si se ha eliminado o no el abonado
     */
    boolean delAbonado(String nif);
    
}
