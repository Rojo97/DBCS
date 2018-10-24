/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.ejb.Remote;

/**
 *
 * @author Ismael Perez
 */
@Remote
public interface BeanGestionarUsuariosRemote {

    boolean isAbonado(String login);

    boolean isEmpleado(String login);

    boolean isPsswdOK(String login, String passwd, String tipoUser);

    String getNif(String login);

    boolean addAbonado(String nif, String nombre, String apellidos, String login, String passwd);

    boolean delAbonado(String nif);
    
}
