/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jse4;

import ejb.HolaMundoRemote;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


/**
 *
 * @author rojo
 */
public class JSE4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException {
        // TODO code application logic here
        Properties prop = new Properties();
        prop.setProperty("org.omg.CORBA.ORBInitialHost","localhost");
        prop.setProperty("org.omg.CORBA.ORBInitialPort","3700");
        try { 
            Context context = new InitialContext(prop);
            HolaMundoRemote bean = (HolaMundoRemote) context.lookup("java:global/EnterpriseApp4/EnterpriseApp4-ejb/HolaMundo");
            System.out.println(bean.hola());
        } catch (NamingException ex) {
            Logger.getLogger(JSE4.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
    
}
