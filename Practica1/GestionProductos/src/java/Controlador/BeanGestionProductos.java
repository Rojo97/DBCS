/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.*;
import Persistencia.*;
import java.util.ArrayList;
import java.util.List;
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
    public List<Vino> getVinos(String categoria, String denOrigen) {
        List<Vino> vinos = vinoFacade.getVinos();
        List<Vino> vinosFiltrado = new ArrayList<>();
        Vino vino;
        for(int i= 0; i< vinos.size(); i++){
            vino = vinos.get(i);
            if(vino.getCategoria().getNombre().equals(categoria)&& vino.getIddenominacion().getNombre().equals(denOrigen)){
                vinosFiltrado.add(vino);
            }
        }
        return vinosFiltrado;
    }

    @Override
    public List<Referencia> getReferencias(int vinoId) {
        Vino vino = vinoFacade.getVinoPorId(Integer.toString(vinoId));
        if(vino != null){
            return (List<Referencia>) vino.getReferenciaCollection();  
        } else {
            return null;
        }
    }

    @Override
    public List<Preferencia> getPrefrencias(String login) {
        String nif = beanGestionarUsuarios.getNif(login);
        return preferenciaFacade.getPreferencias(nif);
    }
    @Override
    public String hola(){
        return "hola";
    }
    
    
}
