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
 * Interfaz del EJB BeanGestionPedidos
 * @author vicrojo
 * @author ismpere
 */
@Local
public interface BeanGestionPedidosLocal {

    /**
     * Añade a la base de datos un pedido pendiente con los atributos proporcionados
     * @param login del usuario
     * @param fecha del pedido
     * @param importe del pedido
     * @param referencia correspondiente al vino
     * @return true si se ha agregado correctamente, false si no se ha podido añadir
    */
    boolean addPedido(String login, String fecha, float importe, int referencia);
   
    /**
     * Devuelve una lista con todos los pedidos pendientes
     * @return List<Pedido> con los pedidos pendientes de la base de datos
     */
    List<Pedido> getPedidosPendientes();
    
    
    /**
     * Cambia el estado de un pedido por otro
     * @param numPedido numero del pedido
     * @param nuevoEstado estado al que cambiarlo
     * @return true si se ha modificado correctamente, false si ha habido algun error
     */
    boolean  editPedido(int  numPedido,  String  nuevoEstado);
    
}
