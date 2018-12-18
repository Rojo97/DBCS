/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.EstadoPedido;
import Dominio.Pedido;
import java.util.List;
import javax.ejb.Local;
import javax.persistence.EntityManager;

/**
 *
 * @author rojo
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
    
    boolean addPedido(String login, String fecha, float importe, int referencia, EstadoPedido estado);
    
    boolean updatePedido(int numPedido, EstadoPedido estado);
    
}
