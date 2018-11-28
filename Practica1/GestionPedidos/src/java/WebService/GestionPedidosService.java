/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebService;

import EJB.Dominio.Pedido;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import EJB.Controlador.BeanGestionPedidosLocal;

/**
 *
 * @author rojo
 */
@WebService(serviceName = "GestionPedidosService")
@Stateless()
public class GestionPedidosService {

    @EJB
    private BeanGestionPedidosLocal ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "addPedido")
    public boolean addPedido(@WebParam(name = "login") String login, @WebParam(name = "fecha") String fecha, @WebParam(name = "importe") float importe, @WebParam(name = "referencia") int referencia) {
        return ejbRef.addPedido(login, fecha, importe, referencia);
    }

    @WebMethod(operationName = "getPedidosPendientes")
    public List<Pedido> getPedidosPendientes() {
        return ejbRef.getPedidosPendientes();
    }

    @WebMethod(operationName = "editPedido")
    public boolean editPedido(@WebParam(name = "numPedido") int numPedido, @WebParam(name = "nuevoEstado") String nuevoEstado) {
        return ejbRef.editPedido(numPedido, nuevoEstado);
    }
    
}
