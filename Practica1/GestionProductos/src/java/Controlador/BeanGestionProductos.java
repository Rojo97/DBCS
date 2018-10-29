/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.*;
import java.awt.List;
import javax.ejb.Stateless;

/**
 *
 * @author rojo
 */
@Stateless
public class BeanGestionProductos implements BeanGestionProductosLocal {

    @Override
    public java.util.List<Vino> getVinos(String categoria, String denOrigen) {
        return null;
    }

    @Override
    public java.util.List<Referencia> getReferencias(int vinold) {
        return null;
    }

    @Override
    public java.util.List<Preferencia> getPrefrencias(String login) {
        return null;
    }
    
    
}
