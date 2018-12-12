/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteejercicio3;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Jersey REST client generated for REST resource:AtributosEnURLResource
 * [AtributosEnURL]<br>
 * USAGE:
 * <pre>
 *        ClienteRest client = new ClienteRest();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ismpere
 */
public class ClienteRest {
    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/Ejercicio3Avanzado/webresources";

    public ClienteRest() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("AtributosEnURL");
    }

    public String getText() throws ClientErrorException {
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
    }

    public void putText(Object requestEntity) throws ClientErrorException {
        webTarget.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public String listVinos(String usuario, String categoria) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("list/{0}/{1}", new Object[]{usuario, categoria}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public String getMensaje(String usuario) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{usuario}));
        return resource.request(javax.ws.rs.core.MediaType.TEXT_HTML).get(String.class);
    }

    public void addMensaje(String usuario, String saludo) throws ClientErrorException {
        WebTarget resource = webTarget;
        resource = resource.path("addMensaje/"+usuario);
        resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).post(javax.ws.rs.client.Entity.entity(saludo, javax.ws.rs.core.MediaType.TEXT_PLAIN));
    }

    public void close() {
        client.close();
    }
    
}
