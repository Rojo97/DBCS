/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EJB.Persistencia;

import EJB.Dominio.EstadoPedido;
import java.util.List;
import javax.ejb.Local;

/**
 * @author vicrojo
 * @author ismpere
 */
@Local
public interface EstadoPedidoFacadeLocal {

    void create(EstadoPedido estadoPedido);

    void edit(EstadoPedido estadoPedido);

    void remove(EstadoPedido estadoPedido);

    EstadoPedido find(Object id);

    List<EstadoPedido> findAll();

    List<EstadoPedido> findRange(int[] range);

    int count();

    /**
     * Busca el EstadoPedido correspondiente a la cadena dada
     * @param cadenaEstado nombre del estado
     * @return EstadoPedido con la cadena proporcionada
     */
    EstadoPedido getEstado(String cadenaEstado);
    
}
