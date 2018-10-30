/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Abonado;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Ismael Perez
 */
@Remote
public interface AbonadoFacadeRemote {

    void create(Abonado abonado);

    void edit(Abonado abonado);

    void remove(Abonado abonado);

    Abonado find(Object id);

    List<Abonado> findAll();

    List<Abonado> findRange(int[] range);

    int count();
    
    Abonado getAbonado(String login);

    boolean addAbonado(Abonado abonado);
    
    boolean delAbonado(String nif);
}
