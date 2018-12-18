/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servivio;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author rojo
 */
@Path("getHtml")
public class SimpleRootResource {
    
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SimpleRootResource
     */
    public SimpleRootResource() {
    }

    /**
     * Retrieves representation of an instance of servivio.SimpleRootResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "Hola mundo desde texto";
    }

    /**
     * PUT method for updating or creating an instance of SimpleRootResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes({"text/plain", "application/json"})
    public void putXml(String content) {
        System.out.println(content);
    }
    
    /**
     * POST method for creating an instamce of SimpleRootResource
     * @param content 
     */
    @POST
    @Consumes({"text/plain", "application/json"})
    public void postXD(String content) {
        System.out.println(content);
    }
    
    @Path("{usuario}")
    @GET
    @Produces("text/html")
    public String getMensaje(@PathParam("usuario") String user){
        return  "<html><body><h1>Hola "  + user +  "</h1></body></hmtl>";
    }
    
    @POST
    @Consumes("text/plain")
    @Path("addMensaje/{usuario}")
    public void addMensaje(@PathParam("usuario") String user, String saludo) {
        System.out.println(saludo + " " + user);
    }
    
    @POST
    @Path("form")
    public void verFormulario(@FormParam("usuario") String nombre, @FormParam("clave") String clave) {
        System.out.println("Nombre: " + nombre + " clave: " + clave);
    }
    
}
