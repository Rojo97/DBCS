/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Referencia;
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
public class ReferenciaFacade extends AbstractFacade<Referencia> implements ReferenciaFacadeLocal {

    @PersistenceContext(unitName = "GestionProductosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReferenciaFacade() {
        super(Referencia.class);
    }
    
    @Override
    public List<Referencia> getReferencias(int vinoId){
        Query query = em.createNamedQuery("Referencia.findByVino");
        query.setParameter("vinoid", Integer.toString(vinoId));
        List<Referencia> referencias= (List<Referencia>) query.getResultList();
        return referencias;
    }
    
}
