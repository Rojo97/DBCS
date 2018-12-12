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
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

/**
 * REST Web Service
 *
 * @author gamusino
 */
@Path("getHTML")
public class GetHTMLResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetHTMLResource
     */
    public GetHTMLResource() {
    }

    /**
     * Retrieves representation of an instance of src.GetHTMLResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return ("<!DOCTYPE html>\n"
                + "<html>\n"
                + "<body>\n"
                + "\n"
                + "<h1>My First Heading</h1>\n"
                + "\n"
                + "<p>My first paragraph.</p>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    /**
     * PUT method for updating or creating an instance of GetHTMLResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
        System.out.println("Argumento: " + content);
    }
}
