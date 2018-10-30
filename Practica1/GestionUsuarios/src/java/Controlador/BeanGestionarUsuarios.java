/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Dominio.*;
import Persistencia.AbonadoFacade;
import Persistencia.AbonadoFacadeRemote;
import Persistencia.EmpleadoFacadeRemote;
import Persistencia.PersonaFacade;
import Persistencia.PersonaFacadeRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Ismael Perez
 */
@Stateless
public class BeanGestionarUsuarios implements BeanGestionarUsuariosRemote {

    @EJB
    private PersonaFacadeRemote personaFacade;

    @EJB
    private EmpleadoFacadeRemote empleadoFacade;

    @EJB
    private AbonadoFacadeRemote abonadoFacade;

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

    @Override
    public String getNif(String login) {
        try{
            Abonado abonado = abonadoFacade.getAbonado(login);
            return abonado.getAbNif().getNif();
        } catch(Exception e){
            return null;
        }
    }

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

    @Override
    public boolean delAbonado(String nif) {
        return abonadoFacade.delAbonado(nif);
    }
    
    
}
