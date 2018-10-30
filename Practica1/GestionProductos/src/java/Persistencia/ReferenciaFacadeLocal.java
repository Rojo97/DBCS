/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Referencia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author rojo
 */
@Local
public interface ReferenciaFacadeLocal {

    void create(Referencia referencia);

    void edit(Referencia referencia);

    void remove(Referencia referencia);

    Referencia find(Object id);

    List<Referencia> findAll();

    List<Referencia> findRange(int[] range);

    int count();
    
    List<Referencia> getReferencias(int vinoId);
    
}
