<%-- 
    Document   : empleado
    Created on : 13-nov-2018, 12:08:36
    Author     : Ismael Perez
--%>

<%@page import="java.util.List"%>
<%@page import="controlador.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Soy un empleado </h1>
        <%String login = (String) request.getSession().getAttribute("login");%>
        
        <h1>Login: <%=login%></h1>
        
        <form action="Servlet">
            <%List<Pedido> vinos = (List<Pedido>) request.getSession().getAttribute("pedidos");%>
            <h1>**** Lista de pedidos ****</h1>
        
            <% for(Pedido p : vinos) { %>
                <%String idPedido = String.valueOf(p.getPeId());%>
                <%String nifPedido = p.getPeNif();%>
                <%String fechaPedido = p.getFecha();%>
                <%String precioPedido = String.valueOf(p.getImporte());%>
                <%String referenciaPedido = String.valueOf(p.getPeReferencia());%>
                <%String estadoPedido = p.getPeEstado().getEstado();%>
                <input type="radio" name="pedidoPendiente" value=<%=idPedido%>>
                ID: <%=idPedido%> NIF: <%=nifPedido%> Fecha: <%=fechaPedido%> Importe: <%=precioPedido%> Ref: <%=referenciaPedido%>
                Estado: <%=estadoPedido%></p>
            <% } %>
            
            <button type="submit" value="MODPEDIDO" name="modificaPedido"> Modificar pedido </button>
            <button type="submit" value="VEROPCIONES" name="opcionesEmpleado"> Volver a las opciones </button>
            <button type="submit" value="CERRARSESION" name="cerrarSesion"> CerrarSesion </button>
        </form>
    </body>
</html>
