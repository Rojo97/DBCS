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
 * Implementación de la fachada asociada a la Entity Referencia
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class ReferenciaFacade extends AbstractFacade<Referencia> implements ReferenciaFacadeLocal {

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
     * Cosntructor por defecto de la clase ReferenciaFacade
     */
    public ReferenciaFacade() {
        super(Referencia.class);
    }
    
    /**
     * Devuelve una lista de referencias por el identificador de un vino
     * Si no hay ninguna referencia para ese vino, devuelve una lista vacía
     * @param vinoId identificador del vino
     * @return lista de refercnaias del vino que tiene por id el introducido como argumento
     */
    @Override
    public List<Referencia> getReferencias(int vinoId){
        Query query = em.createNamedQuery("Referencia.findByVino");
        query.setParameter("vinoid", Integer.toString(vinoId));
        List<Referencia> referencias= (List<Referencia>) query.getResultList();
        return referencias;
    }
    
}
