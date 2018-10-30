/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.*;
import Persistencia.*;
import java.awt.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author rojo
 */
@Stateless
public class BeanGestionProductos implements BeanGestionProductosLocal {

    @EJB
    private PreferenciaFacadeLocal preferenciaFacade;

    @EJB
    private BeanGestionarUsuariosRemote beanGestionarUsuarios;

    @EJB
    private VinoFacadeLocal vinoFacade;
    
    @EJB
    private ReferenciaFacadeLocal referenciaFacade;

    @Override
    public java.util.List<Vino> getVinos(String categoria, String denOrigen) {
        return vinoFacade.getVinos(categoria, denOrigen);
    }

    @Override
    public java.util.List<Referencia> getReferencias(int vinoId) {
        return referenciaFacade.getReferencias(vinoId);
    }

    @Override
    public java.util.List<Preferencia> getPrefrencias(String login) {
        String nif = beanGestionarUsuarios.getNif(login);
        return preferenciaFacade.getPreferencias(nif);
    }
    
    
}
