/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Abonado;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
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
        Abonado abonado = em.find(Abonado.class, login);
        return abonado;
    }
    
    @Override
    public boolean addAbonado(Abonado abonado){
        Abonado abaux = em.find(Abonado.class, abonado.getAbLogin());
        if(abaux == null){
            try{
                em.persist(abonado);
                return true;
            } catch(Exception e){
                return false;
            }
        } else {
            return false;
        }
        
    }
    
    @Override 
    public boolean delAbonado(String nif){
        boolean encontrado = false;
        Abonado abonado;
        List<Abonado> abonados = (List<Abonado>) em.createQuery("SELECT t FROM Abonado t").getResultList();
        for(int i = 0; i< abonados.size() && encontrado == false; i ++){
            abonado = abonados.get(i);
            if(abonado.getAbNif().getNif().equals(nif)){
                encontrado = true;
                em.remove(abonado);
            }
        }
        return encontrado;
    }
    
}
