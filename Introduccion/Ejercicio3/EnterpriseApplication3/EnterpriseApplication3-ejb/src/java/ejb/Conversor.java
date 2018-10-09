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
public class Conversor implements ConversorRemote {
    final double millasKilometro = 0.621371;

    @Override
    public double toMillas(double kilometros) {
        return kilometros*millasKilometro;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
