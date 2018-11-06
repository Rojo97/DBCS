/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Persistencia;

import Controlador.BeanGestionarUsuariosRemote;
import EJB.Dominio.EstadoPedido;
import EJB.Dominio.Pedido;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementacion de PedidoFacadeLocal
 * @author vicrojo
 * @author ismpere
 */
@Stateless
public class PedidoFacade extends AbstractFacade<Pedido> implements PedidoFacadeLocal {

    @EJB
    private BeanGestionarUsuariosRemote beanGestionarUsuarios;

    @PersistenceContext(unitName = "GestionPedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    
    /**
     * Añade a la base de datos el pedido con las caracteristicas proporcionadas
     * @param login del usuario
     * @param fecha del pedido
     * @param importe del pedido
     * @param referencia del vino
     * @param estado del pedido
     * @return true si se ha añadido correctamente, false en caso contrario
     */
    @Override
    public boolean addPedido(String login, String fecha, float importe, int referencia, EstadoPedido estado){
        Pedido pedido = new Pedido();
        pedido.setPeNif(beanGestionarUsuarios.getNif(login));
        pedido.setFecha(fecha);
        pedido.setImporte(importe);
        pedido.setPeEstado(estado);
        pedido.setPeReferencia(referencia);
        try{
            em.persist(pedido);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    /**
     * Cambia el estado del pedido elegido
     * @param numPedido pedido a cambiar
     * @param estado nuevo
     * @return true si se ha cambiado correctamente, false en caso contrario
     */
    @Override
    public boolean updatePedido(int numPedido, EstadoPedido estado){
        Pedido pedido = em.find(Pedido.class, numPedido);
        pedido.setPeEstado(estado);
        try{
            em.merge(pedido);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
}
