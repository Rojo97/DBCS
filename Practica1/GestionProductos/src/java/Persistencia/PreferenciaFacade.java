/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Preferencia;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author rojo
 */
@Stateless
public class PreferenciaFacade extends AbstractFacade<Preferencia> implements PreferenciaFacadeLocal {

    @PersistenceContext(unitName = "GestionProductosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PreferenciaFacade() {
        super(Preferencia.class);
    }
    
    @Override
    public List<Preferencia> getPreferencias(String nif){
        Query query = em.createNamedQuery("Preferencia.findByNifabonado");
        query.setParameter("nifabonado", nif);
        List<Preferencia> prefrencias=(List<Preferencia>) query.getResultList();
        return prefrencias;
    }
}
