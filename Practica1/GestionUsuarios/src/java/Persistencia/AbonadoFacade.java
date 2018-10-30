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
import javax.persistence.Query;

/**
 *
 * @author Ismael Perez
 */
@Stateless
public class AbonadoFacade extends AbstractFacade<Abonado> implements AbonadoFacadeRemote {

    @PersistenceContext(unitName = "GestionUsuariosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbonadoFacade() {
        super(Abonado.class);
    }
    @Override
    public Abonado getAbonado(String login){
        Query query = em.createNamedQuery("Abonado.findByAbLogin");
        query.setParameter("abLogin", login);
        Abonado abonado = (Abonado) query.getSingleResult();
        return abonado;
    }
    
}
