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
import javax.persistence.Query;

/**
 *
 * @author Ismael Perez
 */
@Stateless
public class EmpleadoFacade extends AbstractFacade<Empleado> implements Persistencia.EmpleadoFacadeRemote {

    @PersistenceContext(unitName = "GestionUsuariosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpleadoFacade() {
        super(Empleado.class);
    }
    
    @Override
    public Empleado getEmpleado(String login) {
        Empleado empleado = em.find(Empleado.class, login);
        return empleado;
    }
    
}
