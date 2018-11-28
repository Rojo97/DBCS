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
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author Ismael Perez
 */
public class ControladorServletLogin extends HttpServlet {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/GestionPedidosService/GestionPedidosService.wsdl")
    private GestionPedidosService_Service service;

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

            String btnCerrarSesion = request.getParameter("cerrarSesion");
                
            String btnAbonado = request.getParameter("abonado");
            String btnEmpleado = request.getParameter("empleado");

            String btnVerCarrito = request.getParameter("carrito");
            String btnHacerPedido = request.getParameter("pedido");
            String btnVolverVinos = request.getParameter("muestraVinos");
            String btnModPedido = request.getParameter("modificaPedido");
            
            String btnEditPedidos = request.getParameter("editPedidos");
            String btnEditUsuarios = request.getParameter("editUsuarios");
            String btnEditEstadoPedido = request.getParameter("editEstadoPedido");
            
            String btnOpcionesEmpleado = request.getParameter("opcionesEmpleado");

            if (btnAbonado != null) {
                login = request.getParameter("login");
                pass = request.getParameter("password");

                procesaAbonado(request, response, login, pass);

            } else if (btnEmpleado != null) {
                login = request.getParameter("login");
                pass = request.getParameter("password");

                procesaEmpleado(request, response, login, pass);
            } else if (btnVerCarrito != null) {
                procesaCarritoAbonado(request, response);
            } else if (btnHacerPedido != null) {
                realizaPedido(request, response);

            } else if (btnVolverVinos != null) {
                muestraVinos(request, response);
            } else if (btnModPedido != null) {
                abrirModificarPedido(request, response);
            } else if (btnEditPedidos != null){
                muestraPedidos(request, response);
            } else if (btnEditUsuarios != null){
                
            } else if(btnEditEstadoPedido != null){
                modificarEstadoPedido(request, response);
            } else if(btnOpcionesEmpleado != null){
                muestraOpcionesEmpleado(request, response);
            } else if(btnCerrarSesion != null){
                cerrarSesion(request, response);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    /**
     * Cierra la sesion y borra el usuario actual
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void cerrarSesion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("login");
        
        request.getRequestDispatcher("index.html").forward(request, response);
    }
    
    /**
     * Procesa el evento para modificar el estado de un pedido
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void modificarEstadoPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pedido p = (Pedido)request.getSession().getAttribute("pedidoSeleccionado");
        
        String nuevoEstado = request.getParameter("estadoPedido");
        
        boolean editado = editPedido(p.getPeId(), nuevoEstado);
        
        request.setAttribute("editado", String.valueOf(editado));
        
        request.getRequestDispatcher("pedidoModificado.jsp").forward(request, response);
    }

    /**
     * Procesa el evento para abrir las posibilidades de modificacion de un pedido
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void abrirModificarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idPedido = request.getParameter("pedidoPendiente");
        
        List<Pedido> pedidos = (List < Pedido >)request.getSession().getAttribute("pedidos");
        Pedido pedidoSeleccionado = pedidos.get(0);
        request.setAttribute("nombrePedido", "Isma");
        for (Pedido p : pedidos) {
            if (idPedido != null) {
                if(idPedido.equals(String.valueOf(p.getPeId()))){
                    pedidoSeleccionado = p;
                }
            }
        }
        
        request.getSession().setAttribute("pedidoSeleccionado", pedidoSeleccionado);
        
        
        request.getRequestDispatcher("modificaPedido.jsp").forward(request, response);
    }
    
    /**
     * Procesa el evento de modificar usuarios
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void muestraUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Usuario.g
        //request.getSession().setAttribute("pedidos", pedidos);

        //request.getRequestDispatcher("editPedidos.jsp").forward(request, response);
    }

    /**
     * Procesa el evento de registrar un nuevo pedido
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void realizaPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Referencia> referencias = (List< Referencia>) request.getSession().getAttribute("referencias");

        for (Referencia r : referencias) {
            String login = (String) request.getSession().getAttribute("login");

            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            System.err.println(date);

            addPedido(login, date, r.getPrecio(), r.getCodigo());
        }

        request.getRequestDispatcher("pedidoRealizado.jsp").forward(request, response);
    }

    /**
     * Procesa el evento del login de un abonado
     * @param request
     * @param response
     * @param login
     * @param pass
     * @throws ServletException
     * @throws IOException 
     */
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

    /**
     * Procesa el evento del login de un empleado
     * @param request
     * @param response
     * @param login
     * @param pass
     * @throws ServletException
     * @throws IOException 
     */
    public void procesaEmpleado(HttpServletRequest request, HttpServletResponse response, String login, String pass)
            throws ServletException, IOException {
        if (login != null && pass != null && login.length() > 0 && pass.length() > 0) {
            boolean existe = beanGestionarUsuarios.isEmpleado(login);

            if (existe) {
                boolean passOk = beanGestionarUsuarios.isPsswdOK(login, pass, "empleado");

                if (passOk) {
                    request.getSession().setAttribute("login", login);
                    
                    muestraOpcionesEmpleado(request, response);
                }

            }
        }
    }
    
    /**
     * Procesa el evento de mostrar las opciones del empleado
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void muestraOpcionesEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("empleado.jsp").forward(request, response);
    }

    /**
     * Procesa el evento de mostrar los pedidos a un empleado
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
    public void muestraPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Pedido> pedidos = getPedidosPendientes();
        request.getSession().setAttribute("pedidos", pedidos);

        request.getRequestDispatcher("editPedidos.jsp").forward(request, response);
    }

    /**
     * Procesa el evento de mostrar los vinos a un abonado
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
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

    /**
     * Procesa el evento de la muestra del carrito de un abonado
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     */
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

    private boolean addPedido(java.lang.String login, java.lang.String fecha, float importe, int referencia) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        controlador.GestionPedidosService port = service.getGestionPedidosServicePort();
        return port.addPedido(login, fecha, importe, referencia);
    }

    private List<Pedido> getPedidosPendientes() {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        controlador.GestionPedidosService port = service.getGestionPedidosServicePort();
        return port.getPedidosPendientes();
    }

    private boolean editPedido(int numPedido, java.lang.String nuevoEstado) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        controlador.GestionPedidosService port = service.getGestionPedidosServicePort();
        return port.editPedido(numPedido, nuevoEstado);
    }
}
