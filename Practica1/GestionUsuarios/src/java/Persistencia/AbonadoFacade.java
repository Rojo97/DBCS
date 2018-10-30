/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Abonado;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementacion de la fachada para la Entity Abonado
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class AbonadoFacade extends AbstractFacade<Abonado> implements AbonadoFacadeRemote {

    @PersistenceContext(unitName = "GestionUsuariosPU")
    private EntityManager em;

    /**
     * Retorna el EntityManager
     * @return el Entity Manager de la Entity asociada a la fachada
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     * Constructor por defecto de la clase AbonadoFacade
     */
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
    
    /**
     * Añade un nuevo abonado
     * Devuelve true si se ha añadido el abonado correctamente y false si no se ha podido añadir
     * @param abonado objeto Abonado con todos sus datos
     * @return un valor booleando para indicar si se ha añadido o no el abonado
     */
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
    
    /**
     * Elimina un abonado por su Nif
     * Devuelve true si el abonado de ha eliminado y false si no se ha eliminado
     * @param nif Nif del abonado
     * @return un valor booleano que indica si se ha eliminado o no el abonado
     */
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
