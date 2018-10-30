/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementación de la fachada asociada a la Entity Persona
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> implements PersonaFacadeRemote {

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
     * Cosntructor por defecto de la clase PersonaFacade
     */
    public PersonaFacade() {
        super(Persona.class);
    }
    
    /**
     * Devuelve una Entity Persona por su login
     * Si no existe ninguna persona con ese login, devuelve null
     * @param nif identificador de la persona
     * @return un objeto Persona el cual tiene por Nif el introducido como parámetro
     */
    @Override
    public Persona getPersona(String nif){
        return em.find(Persona.class, nif);
    }
}
