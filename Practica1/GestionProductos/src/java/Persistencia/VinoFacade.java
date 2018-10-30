/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Referencia;
import Dominio.Vino;
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
public class VinoFacade extends AbstractFacade<Vino> implements VinoFacadeLocal {

    @PersistenceContext(unitName = "GestionProductosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VinoFacade() {
        super(Vino.class);
    }
    
    @Override
    public List<Vino> getVinos(){
        Query query = em.createNamedQuery("Vino.findByCategoriaAndDenOrigen");
        List<Vino> vinos=(List<Vino>) query.getResultList();
        return vinos;
    }
    
    @Override
    public Vino getVinoPorId(String id){
        return em.find(Vino.class, id);
    }
}
