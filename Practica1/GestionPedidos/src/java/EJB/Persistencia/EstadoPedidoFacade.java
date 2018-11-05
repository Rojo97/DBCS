/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Persistencia;

import EJB.Dominio.EstadoPedido;
import EJB.Dominio.Pedido;
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
