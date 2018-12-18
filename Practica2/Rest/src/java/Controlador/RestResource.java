/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.Abonado;
import Dominio.EstadoPedido;
import Dominio.Pedido;
import Dominio.Preferencia;
import Persistencia.AbonadoFacadeLocal;
import Persistencia.EstadoPedidoFacadeLocal;
import Persistencia.PedidoFacadeLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.JsonObject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author rojo
 */
@Path("Rest")
public class RestResource {

    PedidoFacadeLocal pedidoFacade = lookupPedidoFacadeLocal();

    EstadoPedidoFacadeLocal estadoPedidoFacade = lookupEstadoPedidoFacadeLocal();

    AbonadoFacadeLocal abonadoFacade = lookupAbonadoFacadeLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RestResource
     */
    public RestResource() {
    }

    /**
     * Retrieves representation of an instance of Controlador.RestResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "Hola mundo";
    }

    /**
     * PUT method for updating or creating an instance of RestResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * {
     * "id" : "uno", "fecha" : "2015-02-11", "importe": "25.26", "referencia" :
     * 1 }
     *
     * @param pedido
     * @return
     */
    @Path("/abonado/{id}/addPedido")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPedido(@PathParam("id") String id, JsonObject pedido) {
        String login = id;
        String fecha = pedido.getString("fecha");
        float importe = Float.valueOf(pedido.getString("importe"));
        int referencia = pedido.getInt("referencia");
        EstadoPedido estado = estadoPedidoFacade.getEstado("pendiente");
        boolean correcto = pedidoFacade.addPedido(login, fecha, (float) importe, referencia, estado);
        if (correcto) {
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("empleado/pedidosPendientes")
    @GET
    @Produces("application/json")
    public Response getPedidosPendientes() {
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        EstadoPedido estadoPedido = estadoPedidoFacade.getEstado("pendiente");
        List<Pedido> pedidosList = (List<Pedido>) estadoPedido.getPedidoCollection();
        Pedido[] pedidos = new Pedido[pedidosList.size()];
        for (int i = 0; i < pedidosList.size(); i++) {
            pedidos[i] = pedidosList.get(i);
        }
        System.err.println(pedidosList.size());
        if (pedidosList == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
        respuesta.header("Access-Control-Expose-Headers", "*");
        respuesta.type("application/json");
        respuesta.entity(pedidos);
        return respuesta.build();
    }

    @Path("abonado/{id}/preferencias")
    @GET
    @Produces("application/json")
    public Response getPreferenciasId(@PathParam("id") String id) {
        Abonado abonado = abonadoFacade.getAbonado(id);
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        if (abonado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            List<Preferencia> preferenciasList = (List<Preferencia>) abonado.getPreferenciaCollection();
            Preferencia[] preferencias = new Preferencia[preferenciasList.size()];
            for (int i = 0; i < preferenciasList.size(); i++) {
                preferencias[i] = preferenciasList.get(i);
            }
            respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
            respuesta.header("Access-Control-Expose-Headers", "*");
            respuesta.type("application/json");
            respuesta.entity(preferencias);
            return respuesta.build();

        }
    }

    @Path("empleado/pedidos/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response modPedido(@PathParam("id") int id, String estado) {
        EstadoPedido estadoPedido = estadoPedidoFacade.getEstado(estado);
        boolean correcto = pedidoFacade.updatePedido(id, estadoPedido);
        if(estadoPedido == null || correcto == false ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            return Response.status(Response.Status.OK).build();
        }
    }
    
    @DELETE
    @Path("empleado/pedidos/{id}/delete")
    @Consumes("text/plain")
    @Produces("application/json")
    public Response deletePedido(@PathParam("id") int id) {
        Pedido pedido = pedidoFacade.find(id);
        if(pedido == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }else {
            pedidoFacade.remove(pedido);
            return Response.status(Response.Status.OK).build();
        }
    }

    @Path("isAbonado")
    @GET
    @Produces("application/json")
    public Response isAbonado(@QueryParam("id") String id) {
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        try {
            Abonado abonado = abonadoFacade.getAbonado(id);
            if (abonado == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
            respuesta.header("Access-Control-Expose-Headers", "*");
            respuesta.type("application/json");
            respuesta.entity(abonado);
            return respuesta.build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    private AbonadoFacadeLocal lookupAbonadoFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (AbonadoFacadeLocal) c.lookup("java:global/Rest/AbonadoFacade!Persistencia.AbonadoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private EstadoPedidoFacadeLocal lookupEstadoPedidoFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (EstadoPedidoFacadeLocal) c.lookup("java:global/Rest/EstadoPedidoFacade!Persistencia.EstadoPedidoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PedidoFacadeLocal lookupPedidoFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PedidoFacadeLocal) c.lookup("java:global/Rest/PedidoFacade!Persistencia.PedidoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
