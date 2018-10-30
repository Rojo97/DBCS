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
 * Implementación de la fachada asociada a la Entity Preferencia
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class PreferenciaFacade extends AbstractFacade<Preferencia> implements PreferenciaFacadeLocal {

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
     * Constructor por defecto de la clase PreferenciaFacade
     */
    public PreferenciaFacade() {
        super(Preferencia.class);
    }
    
    /**
     * Devuelve una lista de preferencias por el nif de un usuario
     * Si no hay ninguna preferencia, devuelve una lista vacía
     * @param nif Nif del usuario
     * @return lista de preferencias del usuario que tiene por Nif el pasado como argumento
     */
    @Override
    public List<Preferencia> getPreferencias(String nif){
        Query query = em.createNamedQuery("Preferencia.findByNifabonado");
        query.setParameter("nifabonado", nif);
        List<Preferencia> prefrencias=(List<Preferencia>) query.getResultList();
        return prefrencias;
    }
}
