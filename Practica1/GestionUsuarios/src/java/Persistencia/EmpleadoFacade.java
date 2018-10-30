/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Dominio.Empleado;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementación de la fachada asociada a la Entity Empleado
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> implements Persistencia.EmpleadoFacadeRemote {

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
     * Cosntructor por defecto de la clase EmpleadoFacade
     */
    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    /**
     * Devuelve una Entity Empleado por su login
     * Si no existe ningun empleado con ese login, devuelve null
     * @param login identificador del empleado
     * @return un objeto Empleado el cual tiene por login el introducido como parámetro
     */
    @Override
    public Empleado getEmpleado(String login) {
        Empleado empleado = em.find(Empleado.class, login);
        return empleado;
    }
    
}
