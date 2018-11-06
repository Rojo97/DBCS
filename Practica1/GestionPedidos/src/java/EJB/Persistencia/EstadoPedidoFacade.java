/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Persistencia;

import EJB.Dominio.EstadoPedido;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Implementacion de EstadoPedidoFacadeLocal
 * @author vicrojo
 * @author ismpere
 */
@Stateless
public class EstadoPedidoFacade extends AbstractFacade<EstadoPedido> implements EstadoPedidoFacadeLocal {

    @PersistenceContext(unitName = "GestionPedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstadoPedidoFacade() {
        super(EstadoPedido.class);
    }
    
    /**
     * Busca el EstadoPedido correspondiente a la cadena dada
     * @param cadenaEstado nombre del estado
     * @return EstadoPedido con la cadena proporcionada
     */
    @Override
    public EstadoPedido getEstado(String cadenaEstado){
        Query query = em.createNamedQuery("EstadoPedido.findByEstado");
        query.setParameter("estado", cadenaEstado);
        try{
            EstadoPedido estado = (EstadoPedido) query.getSingleResult();
            return estado;
        }catch(Exception e){
            return null;
        }
    }
    
}
