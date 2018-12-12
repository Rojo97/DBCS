/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author ismpere
 */
@Path("AtributosEnURL")
public class AtributosEnURLResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AtributosEnURLResource
     */
    public AtributosEnURLResource() {
    }

    /**
     * Retrieves representation of an instance of src.SimpleRootResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return ("Hola estoy funcionando desde el servicio RESTful");
    }

    /**
     * PUT method for updating or creating an instance of SimpleRootResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    public void putText(String content) {
        System.out.println("Argumento: " + content);
    }

    /**
     * Devuelve un html de saludo
     *
     * @param user
     * @return un saludo al usuario
     */
    @Path("{usuario}")
    @GET
    @Produces("text/html")
    public String getMensaje(@PathParam("usuario") String user) {
        return ("<html><body><h1>Hola “ + user + “ </h1></body></hmtl>");
    }

    /**
     * Añade un saludo al usuario
     *
     * @param user
     * @param saludo
     */
    @POST
    @Consumes("text/plain")
    @Path("addMensaje/{usuario}")
    public void addMensaje(@PathParam("usuario") String user, String saludo) {
        System.out.println(saludo + " " + user);
    }

    /**
     * Devuelve los vinos
     *
     * @param user
     * @return un saludo al usuario
     */
    @Path("list/{usuario}/{categoria}")
    @GET
    @Produces("text/html")
    public String listVinos(@PathParam("usuario") String user, @PathParam("categoria") String tipo) {
        return ("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "\n"
                + "<h1>Nombre: " + user + "</h1>\n"
                + "\n"
                + "<p>Categoria: " + tipo + "</p>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }
}
