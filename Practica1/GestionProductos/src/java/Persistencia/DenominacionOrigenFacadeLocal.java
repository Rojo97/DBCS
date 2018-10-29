/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.DenominacionOrigen;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rojo
 */
@Local
public interface DenominacionOrigenFacadeLocal {

    void create(DenominacionOrigen denominacionOrigen);

    void edit(DenominacionOrigen denominacionOrigen);

    void remove(DenominacionOrigen denominacionOrigen);

    DenominacionOrigen find(Object id);

    List<DenominacionOrigen> findAll();

    List<DenominacionOrigen> findRange(int[] range);

    int count();
    
}
