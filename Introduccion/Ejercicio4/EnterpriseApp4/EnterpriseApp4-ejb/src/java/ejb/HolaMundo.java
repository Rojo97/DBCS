/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;

/**
 *
 * @author rojo
 */
@Stateless
public class HolaMundo implements HolaMundoRemote {

    @Override
    public String hola() {
        return "Hola mundo dessde un javabean";
    }
    
}
