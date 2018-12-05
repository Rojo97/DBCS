/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pack;

/**
 *
 * @author rojo
 */
public class main {
    public static void main(String[] args){
        TestRest servicio = new TestRest();
        
        String respuesta = servicio.getXml();
        System.err.println(respuesta);
        
        servicio.putXml_TEXT("HEY OLA");
    }
}
