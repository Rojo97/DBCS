/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.Abonado;
import Persistencia.AbonadoFacadeLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 * REST Web Service
 *
 * @author rojo
 */
@Path("Rest")
public class RestResource {

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
            respuesta.header("Access-Control-Allow-Origin","http://localhost:8383");
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
}
