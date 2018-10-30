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
 * Implementación de las operaciones del componente GestionProductos
 * @author ismpere
 * @author vicrojo
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

    /**
     * Devuelve una lista de vinos por su categoría y denominación de origen
     * Si no encuentra ningun vino con esas características devuelve una lista vacía
     * @param categoria categoría de los vinos
     * @param denOrigen denominación de origen de los vinos
     * @return una lista con los vinos que cumplen esas dos características
     */
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

    /**
     * Devuelve las referencias por el identificador de un vino
     * Si no hay ninguna referencia, devuelve una lista vacía
     * @param vinoId identificador del vino
     * @return una lista con las referencias de ese vino
     */
    @Override
    public List<Referencia> getReferencias(int vinoId) {
        Vino vino = vinoFacade.getVinoPorId(Integer.toString(vinoId));
        if(vino != null){
            return (List<Referencia>) vino.getReferenciaCollection();  
        } else {
            return null;
        }
    }

    /**
     * Devuelve una lista de preferencias por el login de un usuario
     * @param login identificador del usuario
     * @return una lista de preferencias del usuario que tiene como login el introducido
     */
    @Override
    public List<Preferencia> getPrefrencias(String login) {
        String nif = beanGestionarUsuarios.getNif(login);
        return preferenciaFacade.getPreferencias(nif);
    }
    
    /**
     * Método de prueba para comprobar el componente local
     * @return mensaje de prueba "hola"
     */
    @Override
    public String hola(){
        return "hola";
    }
    
    
}
