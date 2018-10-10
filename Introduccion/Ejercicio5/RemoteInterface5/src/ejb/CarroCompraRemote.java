/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Remote;

/**
 *
 * @author rojo
 */
@Remote
public interface CarroCompraRemote {

    void addCarro(String item, int cant);

    String getCarro();
    
    public void deleteCarro();
    
}
