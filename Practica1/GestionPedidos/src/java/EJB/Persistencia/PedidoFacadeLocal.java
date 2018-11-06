/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Persistencia;

import EJB.Dominio.EstadoPedido;
import EJB.Dominio.Pedido;
import java.util.List;
import javax.ejb.Local;

/**
 * @author vicrojo
 * @author ismpere
 */
@Local
public interface PedidoFacadeLocal {

    void create(Pedido pedido);

    void edit(Pedido pedido);

    void remove(Pedido pedido);

    Pedido find(Object id);

    List<Pedido> findAll();

    List<Pedido> findRange(int[] range);

    int count();

    /**
     * Añade a la base de datos el pedido con las caracteristicas proporcionadas
     * @param login del usuario
     * @param fecha del pedido
     * @param importe del pedido
     * @param referencia del vino
     * @param estado del pedido
     * @return true si se ha añadido correctamente, false en caso contrario
     */
    boolean addPedido(String login, String fecha, float importe, int referencia, EstadoPedido estado);
    
    /**
     * Cambia el estado del pedido elegido
     * @param numPedido pedido a cambiar
     * @param estado nuevo
     * @return true si se ha cambiado correctamente, false en caso contrario
     */
    boolean updatePedido(int numPedido, EstadoPedido estado);
    
}
