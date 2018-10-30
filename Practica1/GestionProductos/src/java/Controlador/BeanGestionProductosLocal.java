/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.*;
import java.awt.List;
import javax.ejb.Local;

/**
 *
 * @author rojo
 */
@Local
public interface BeanGestionProductosLocal {

    java.util.List<Vino> getVinos(String categoria, String denOrigen);

    java.util.List<Referencia> getReferencias(int vinold);

    java.util.List<Preferencia> getPrefrencias(String login);
    
    String hola();

}
