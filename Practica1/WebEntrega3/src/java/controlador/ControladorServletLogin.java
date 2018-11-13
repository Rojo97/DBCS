/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import Controlador.BeanGestionProductosLocal;
import Controlador.BeanGestionarUsuariosRemote;
import Dominio.Categoria;
import Dominio.DenominacionOrigen;
import Dominio.Preferencia;
import Dominio.Referencia;
import Dominio.Vino;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ismael Perez
 */
public class ControladorServletLogin extends HttpServlet {

    @EJB
    private BeanGestionProductosLocal beanGestionProductos;

    @EJB
    private BeanGestionarUsuariosRemote beanGestionarUsuarios;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String login = "";
            String pass = "";

            String btnAbonado = request.getParameter("abonado");
            String btnEmpleado = request.getParameter("empleado");

            String btnVerCarrito = request.getParameter("carrito");
            String btnHacerPedido = request.getParameter("pedido");
            String btnVolverVinos = request.getParameter("muestraVinos");

            if (btnAbonado != null) {
                login = request.getParameter("login");
                pass = request.getParameter("password");

                procesaAbonado(request, response, login, pass);

            } else if (btnEmpleado != null) {
                login = request.getParameter("login");

                if (login != null && login.length() > 0) {
                    boolean existe = beanGestionarUsuarios.isEmpleado(login);
                    request.setAttribute("login", login);
                    request.setAttribute("abonado", existe);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }
            } else if (btnVerCarrito != null) {
                procesaCarritoAbonado(request, response);
            } else if (btnHacerPedido != null) {
                realizaPedido(request, response);
                
            } else if(btnVolverVinos != null) {
                muestraVinos(request, response);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    public void realizaPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Referencia> referencias = (List < Referencia >)request.getSession().getAttribute("referencias");
        
        for(Referencia r : referencias){
            String login = (String) request.getSession().getAttribute("login");
            
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());

            //addPedido(login, date, r.getPrecio(), r.getCodigo());
        }
        
        request.getRequestDispatcher("pedidoRealizado.jsp").forward(request, response);
    }

    public void procesaAbonado(HttpServletRequest request, HttpServletResponse response, String login, String pass)
            throws ServletException, IOException {
        if (login != null && pass != null && login.length() > 0 && pass.length() > 0) {
            boolean existe = beanGestionarUsuarios.isAbonado(login);

            if (existe) {
                boolean passOk = beanGestionarUsuarios.isPsswdOK(login, pass, "abonado");

                if (passOk) {
                    request.getSession().setAttribute("login", login);

                    muestraVinos(request, response);
                }

            }
        }
    }

    public void muestraVinos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String login = (String) request.getSession().getAttribute("login");
        ArrayList<Vino> vinos = new ArrayList<>();
        List<Preferencia> preferencias = beanGestionProductos.getPrefrencias(login);
        for (Preferencia p : preferencias) {
            Categoria c = p.getCategoria();
            DenominacionOrigen d = p.getIddenominacion();
            vinos.addAll(beanGestionProductos.getVinos(c.getNombre(), d.getNombre()));
        }

        request.getSession().setAttribute("vinos", vinos);

        request.getRequestDispatcher("abonado.jsp").forward(request, response);
    }

    public void procesaCarritoAbonado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] nombresVinosSeleccionados = request.getParameterValues("vino");

        List<Vino> vinos = (List< Vino>) request.getSession().getAttribute("vinos");
        ArrayList<Vino> vinosSeleccionados = new ArrayList<>();
        ArrayList<Referencia> referencias = new ArrayList<>();
        for (Vino v : vinos) {
            if (nombresVinosSeleccionados != null) {
                for (String n : nombresVinosSeleccionados) {
                    if (n != null && n.equals(v.getNombrecomercial())) {
                        vinosSeleccionados.add(v);
                        referencias.addAll(beanGestionProductos.getReferencias(v.getId()));
                        break;
                    }
                }
            }
        }

        request.setAttribute("nVinos", String.valueOf(vinosSeleccionados.size()));

        request.getSession().setAttribute("referencias", referencias);

        request.setAttribute("vinosSeleccionados", vinosSeleccionados);

        request.getRequestDispatcher("carritoAbonado.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
