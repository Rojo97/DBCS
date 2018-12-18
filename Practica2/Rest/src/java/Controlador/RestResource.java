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
import Dominio.Referencia;
import Dominio.Vino;
import Persistencia.AbonadoFacadeLocal;
import Persistencia.EstadoPedidoFacadeLocal;
import Persistencia.PedidoFacadeLocal;
import Persistencia.VinoFacadeLocal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * @author vicrojo
 * @author ismpere
 */
@Path("Rest")
public class RestResource {

    VinoFacadeLocal vinoFacade = lookupVinoFacadeLocal();

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
     * Aniade un pedido a la BD
     * @param pedido con la referencia del mismo
     * @return 201 si se ha creado, 404 en otro caso
     */
    @Path("/abonado/{id}/addPedido")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPedido(@PathParam("id") String id, JsonObject pedido) {
        String login = id;
        DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String fecha = formato.format(date);
        int referencia = pedido.getInt("referencia");
        EstadoPedido estado = estadoPedidoFacade.getEstado("pendiente");
        boolean correcto = pedidoFacade.addPedido(login, fecha, referencia, estado);
        if (correcto) {
            ResponseBuilder respuesta = Response.status(Response.Status.CREATED);
            respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
            return respuesta.build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     * Obtine los pedidos con estado pendiente
     * @return pedidos
     */
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

    /**
     * Obtiene las preferencias de un usuario
     * @param id
     * @return preferencias
     */
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

    /**
     * Cambia el estado de un pedido por otro
     * @param id del pedido
     * @param estado a poner
     * @return 404 si no hay pedido, 200 si se ha cambiado
     */
    @Path("empleado/pedidos/{id}")
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response modPedido(@PathParam("id") int id, JsonObject estado) {
        EstadoPedido estadoPedido = estadoPedidoFacade.getEstado(estado.getString("estado"));
        boolean correcto = pedidoFacade.updatePedido(id, estadoPedido);
        if (estadoPedido == null || correcto == false) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.status(Response.Status.OK).build();
        }
    }

    /**
     * Elimina un pedido de la DB
     * @param id del pedido
     * @return 404 si no encuentra el pedido, 200 si se ha podido borrar
     */
    @DELETE
    @Path("empleado/pedidos/{id}")
    @Consumes("application/json")
    @Produces("application/json")
    public Response deletePedido(@PathParam("id") int id) {
        Pedido pedido = pedidoFacade.find(id);
        if (pedido == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            pedidoFacade.remove(pedido);
            return Response.status(Response.Status.OK).build();
        }
    }
    
    /**
     * Obtiene la referencia de un vino
     * @param id del vino
     * @return referencia
     */
    @Path("vino/{id}/referencia")
    @GET
    @Produces("application/json")
    public Response getReferencia(@PathParam("id") String id) {
        Vino vino = vinoFacade.getVino(id);
        List<Referencia> referencias = (List<Referencia>) vino.getReferenciaCollection();
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        if (referencias == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Referencia referencia = referencias.get(0);
            respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
            respuesta.header("Access-Control-Expose-Headers", "*");
            respuesta.type("application/json");
            respuesta.entity(referencia);
            return respuesta.build();

        }
    }

    /**
     * Obtinene los vinos que coinciden con las preferecias del abonado
     * @param id del abonado
     * @return vinos
     */
    @Path("abonado/{id}/preferencias/vinos")
    @GET
    @Produces("application/json")
    public Response getVinosAbonado(@PathParam("id") String id) {
        Abonado abonado = abonadoFacade.getAbonado(id);
        if (abonado == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        List<Preferencia> preferenciasList = (List<Preferencia>) abonado.getPreferenciaCollection();
        List<Vino> vinos = new ArrayList<Vino>();
        for (int i = 0; i < preferenciasList.size(); i++) {
            List<Vino> vinotemp = getVinos(preferenciasList.get(i).getCategoria().getNombre(), preferenciasList.get(i).getIddenominacion().getNombre());
            for (int j = 0; j < vinotemp.size(); j++) {
                if (!(vinos.contains(vinotemp.get(j)))) {
                    vinos.add(vinotemp.get(j));
                }
            }
        }
        Vino[] vinosArray = new Vino[vinos.size()];
        for(int i = 0; i<vinos.size(); i++){
            vinosArray[i]=vinos.get(i);
        }
        ResponseBuilder respuesta = Response.status(Response.Status.ACCEPTED);
        respuesta.header("Access-Control-Allow-Origin", "http://localhost:8383");
        respuesta.header("Access-Control-Expose-Headers", "*");
        respuesta.type("application/json");
        respuesta.entity(vinosArray);
        return respuesta.build();
    }
    
        /**
     * Obtinene los vinos correspondientes a una categoria y a una denominacion de origen
     * @param categoria categoria
     * @param denOrigen origen
     * @return Lista de vinos
     */
    public List<Vino> getVinos(String categoria, String denOrigen) {
        List<Vino> vinos = vinoFacade.findAll();
        List<Vino> vinosFiltrado = new ArrayList<>();
        Vino vino;
        for (int i = 0; i < vinos.size(); i++) {
            vino = vinos.get(i);
            if (vino.getCategoria().getNombre().equals(categoria) && vino.getIddenominacion().getNombre().equals(denOrigen)) {
                vinosFiltrado.add(vino);
            }
        }
        return vinosFiltrado;
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
    


    private VinoFacadeLocal lookupVinoFacadeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (VinoFacadeLocal) c.lookup("java:global/Rest/VinoFacade!Persistencia.VinoFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
