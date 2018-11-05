/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Controlador;

import EJB.Dominio.Pedido;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rojo
 */
@Local
public interface BeanGestionProductosLocal {

    boolean addPedido(String login, String fecha, float importe, int referencia);
    
    List<Pedido> getPedidosPendientes();
    
    boolean  editPedido(int  numPedido,  String  nuevoEstado);
    
}
