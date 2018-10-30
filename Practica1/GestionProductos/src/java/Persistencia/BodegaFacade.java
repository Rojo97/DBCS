/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Bodega;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementación de la fachada asociada a la Entity Bodega
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class BodegaFacade extends AbstractFacade<Bodega> implements BodegaFacadeLocal {

    @PersistenceContext(unitName = "GestionProductosPU")
    private EntityManager em;

    /**
     * Retorna el EntityManager
     * @return el Entity Manager de la Entity asociada a la fachada
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Constructor por defecto de la clase BodegaFacade
     */
    public BodegaFacade() {
        super(Bodega.class);
    }
    
}
