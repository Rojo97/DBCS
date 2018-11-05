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
 *
 * @author rojo
 */
@Stateless
public class BeanGestionProductos implements BeanGestionProductosLocal {

    @EJB
    private PedidoFacadeLocal pedidoFacade;

    @EJB
    private EstadoPedidoFacadeLocal estadoPedidoFacade;

    @Override
    public boolean addPedido(String login, String fecha, float importe, int referencia) {
        EstadoPedido estado = estadoPedidoFacade.getEstado("pendiente");
        return pedidoFacade.addPedido(login, fecha, importe, referencia, estado);   
    }

    @Override
    public  List<Pedido> getPedidosPendientes(){
        EstadoPedido estado = estadoPedidoFacade.getEstado("pendiente");
        return (List<Pedido>) estado.getPedidoCollection();
    }
    
    @Override
    public boolean editPedido(int  numPedido,  String  nuevoEstado){
        EstadoPedido estado = estadoPedidoFacade.getEstado(nuevoEstado);
        return pedidoFacade.updatePedido(numPedido, estado);
    }
}
