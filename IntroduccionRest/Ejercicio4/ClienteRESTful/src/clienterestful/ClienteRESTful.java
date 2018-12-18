/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienterestful;

/**
 *
 * @author ismpere
 */
public class ClienteRESTful {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RESTfulClient r = new RESTfulClient();
        
        System.out.println("Texto: " + r.getText());
        
        r.putText("Texto de prueba");
    }
    
}
