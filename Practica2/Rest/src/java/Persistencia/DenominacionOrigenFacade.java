/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.DenominacionOrigen;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rojo
 */
@Stateless
public class DenominacionOrigenFacade extends AbstractFacade<DenominacionOrigen> implements DenominacionOrigenFacadeLocal {

    @PersistenceContext(unitName = "RestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DenominacionOrigenFacade() {
        super(DenominacionOrigen.class);
    }
    
}
