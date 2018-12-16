/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Abonado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author rojo
 */
@Stateless
public class AbonadoFacade extends AbstractFacade<Abonado> implements AbonadoFacadeLocal {

    @PersistenceContext(unitName = "RestPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbonadoFacade() {
        super(Abonado.class);
    }
    
        /**
     * Devuelve un abonado por su login
     * Si no se ha encontrado ningún abonado con ese login, devuelve null
     * @param login identificador del abonado
     * @return Abonado que tiene por login el introducido como parámetro
     */
    @Override
    public Abonado getAbonado(String login){
        Abonado abonado = em.find(Abonado.class, login);
        return abonado;
    }
}
