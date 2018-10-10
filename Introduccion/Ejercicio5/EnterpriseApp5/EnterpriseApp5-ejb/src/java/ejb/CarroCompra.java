/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author rojo
 */
@Stateful
public class CarroCompra implements CarroCompraRemote {

    String carro = "";

    @Override
    public void addCarro(String item, int cant) {
        if(!carro.equals("")){
            carro = carro +";";
        }
        carro = carro + item +" = "+cant;
    }

    @Override
    public String getCarro() {
        return carro;
    }

    @Override @Remove
    public void deleteCarro() {
        carro = "";
    }
    
    
    
}
