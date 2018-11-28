/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Controlador;

import EJB.Dominio.EstadoPedido;
import EJB.Dominio.Pedido;
import EJB.Persistencia.EstadoPedidoFacadeLocal;
import EJB.Persistencia.PedidoFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *Implemetaciṕon del componente gestionPedios
 * @author vicrojo
 * @author ismpere
 */
@Stateless
public class BeanGestionPedidos implements BeanGestionPedidosLocal {

    @EJB
    private PedidoFacadeLocal pedidoFacade;

    @EJB
    private EstadoPedidoFacadeLocal estadoPedidoFacade;
    
    /**
     * Añade a la base de datos un pedido pendiente con los atributos proporcionados
     * @param login del usuario
     * @param fecha del pedido
     * @param importe del pedido
     * @param referencia correspondiente al vino
     * @return true si se ha agregado correctamente, false si no se ha podido añadir
    */
    @Override
    public boolean addPedido(String login, String fecha, float importe, int referencia) {
        EstadoPedido estado = estadoPedidoFacade.getEstado("pendiente");
        return pedidoFacade.addPedido(login, fecha, importe, referencia, estado);   
    }
    
    
    /**
     * Devuelve una lista con todos los pedidos pendientes
     * @return List<Pedido> con los pedidos pendientes de la base de datos
     */
    @Override
    public  List<Pedido> getPedidosPendientes(){
        EstadoPedido estado = estadoPedidoFacade.getEstado("pendiente");
        return (List<Pedido>) estado.getPedidoCollection();
    }
    
    /**
     * Cambia el estado de un pedido por otro
     * @param numPedido numero del pedido
     * @param nuevoEstado estado al que cambiarlo
     * @return true si se ha modificado correctamente, false si ha habido algun error
     */
    @Override
    public boolean editPedido(int  numPedido,  String  nuevoEstado){
        EstadoPedido estado = estadoPedidoFacade.getEstado(nuevoEstado);
        return pedidoFacade.updatePedido(numPedido, estado);
    }
}
