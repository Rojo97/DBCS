/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.*;
import Persistencia.AbonadoFacadeRemote;
import Persistencia.EmpleadoFacadeRemote;
import Persistencia.PersonaFacadeRemote;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Implementacion de las funciones para la gestión de usuarios
 * @author ismpere
 * @author vicrojo
 */
@Stateless
public class BeanGestionarUsuarios implements BeanGestionarUsuariosRemote {

    @EJB
    private PersonaFacadeRemote personaFacade;

    @EJB
    private EmpleadoFacadeRemote empleadoFacade;

    @EJB
    private AbonadoFacadeRemote abonadoFacade;

    /**
     * Comprueba si existe un abonado mediante su login
     * Devuelve true si es un abonado y false si no lo es
     * @param login el identificador a comprobar
     * @return un valor booleano de si es o no un abonado
     */
    @Override
    public boolean isAbonado(String login) {
       try{
          Abonado abonado = abonadoFacade.getAbonado(login);
          if(abonado != null){
             return true; 
          } else return false;
        } catch(Exception e){
           return false;
        }
    }

    /**
     * Comprueba si existe un empleado por login
     * Devuelve true si es un empleado y false si no lo es
     * @param login el identificador a comprobar
     * @return un valor booleano de si es o no un empleado
     */
    @Override
    public boolean isEmpleado(String login) {
        try{
            Empleado empleado = empleadoFacade.getEmpleado(login);
            if(empleado != null){
                return true;            
            } else {
                return false;
            }

        } catch(Exception e){
            return false;
        }
    }
    
    /**
     * Comprueba si una contraseña es correcta con el login y el tipo de usuario
     * Devuelve true si la contraseña es correcta y false si no lo es
     * @param login el identificador del usuario
     * @param passwd la contraseña del usuario
     * @param tipoUser el tipo de usuario
     * @return un valor booleano de si es o no correcta la contraseña
     */
    @Override
    public boolean isPsswdOK(String login, String passwd, String tipoUser) {
        if(tipoUser.equals("abonado")){
            try{
               Abonado abonado = abonadoFacade.getAbonado(login);
               if(abonado.getAbPasswd().equals(passwd)){
                   return true;
               } else { 
                   return false;
               }
            }catch(Exception e){
                return false;
            }
        }else if(tipoUser.equals("empleado")){
            try{
               Empleado empleado = empleadoFacade.getEmpleado(login);
               if(empleado.getEmPasswd().equals(passwd)){
                   return true;
               } else { 
                   return false;
               }
            }catch(Exception e){
                return false;
            }
        } else{
           return false;
        }
    }

    /**
     * Retorna el Nif de un usuario por su login
     * Si el login no pertenece a ningun usuario, devuelve null
     * @param login el identificador del usuario
     * @return el Nif del usuario
     */
    @Override
    public String getNif(String login) {
        try{
            Abonado abonado = abonadoFacade.getAbonado(login);
            return abonado.getAbNif().getNif();
        } catch(Exception e){
            return null;
        }
    }

    /**
     * Añade un nuevo abonado con todos sus datos
     * Devuelve true si el abonado de añadido correctamente y false si no se ha añadido
     * @param nif Nif del abonado
     * @param nombre nombre del abonado
     * @param apellidos apellidos del abonado
     * @param login identificador del abonado
     * @param passwd contraseña del abonado
     * @return un valor booleano que representa si se ha añadido o no el abonado
     */
    @Override
    public boolean addAbonado(String nif, String nombre, String apellidos, String login, String passwd) {
        Persona persona = new Persona(nif);
        persona.setApellidos(apellidos);
        persona.setNombre(nombre);
        Abonado abonado = new Abonado(login);
        abonado.setAbNif(persona);
        abonado.setAbPasswd(passwd);
        return abonadoFacade.addAbonado(abonado);
    }

    /**
     * Elimina un abonado por su Nif
     * Devuelve true si el abonado de ha eliminado y false si no se ha eliminado
     * @param nif Nif del abonado
     * @return un valor booleano de si se ha eliminado o no el abonado
     */
    @Override
    public boolean delAbonado(String nif) {
        return abonadoFacade.delAbonado(nif);
    }
    
    
}
