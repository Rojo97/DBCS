/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Vino;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementación de la fachada asociada a la Entity Vino
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class VinoFacade extends AbstractFacade<Vino> implements VinoFacadeLocal {

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
     * Cosntructor pos defecto de la clase VinoFacade
     */
    public VinoFacade() {
        super(Vino.class);
    }
    
    /**
     * Devuelve la lista de vinos
     * Si no hya ninguno, devuelve una lista vacía
     * @return lista de vinos
     */
    @Override
    public List<Vino> getVinos(){
        Query query = em.createNamedQuery("Vino.findByCategoriaAndDenOrigen");
        List<Vino> vinos=(List<Vino>) query.getResultList();
        return vinos;
    }
    
    /**
     * Devuelve una Entity Vino por su id
     * Si no hay ningún vino con ese id, devuelve null
     * @param id identificador del vino
     * @return vino el cual tiene por id el pasado como argumento
     */
    @Override
    public Vino getVinoPorId(String id){
        return em.find(Vino.class, id);
    }
}
