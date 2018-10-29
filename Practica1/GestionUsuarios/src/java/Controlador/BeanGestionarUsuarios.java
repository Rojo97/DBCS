/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javax.ejb.Stateless;

/**
 *
 * @author Ismael Perez
 */
@Stateless
public class BeanGestionarUsuarios implements BeanGestionarUsuariosRemote {

    @Override
    public boolean isAbonado(String login) {
        return false;
    }

    @Override
    public boolean isEmpleado(String login) {
        return false;
    }

    @Override
    public boolean isPsswdOK(String login, String passwd, String tipoUser) {
        return false;
    }

    @Override
    public String getNif(String login) {
        return null;
    }

    @Override
    public boolean addAbonado(String nif, String nombre, String apellidos, String login, String passwd) {
        return false;
    }

    @Override
    public boolean delAbonado(String nif) {
        return false;
    }
    
    
}
