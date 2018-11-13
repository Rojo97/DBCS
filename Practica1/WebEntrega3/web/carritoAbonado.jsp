<%-- 
    Document   : saludo
    Created on : 03-oct-2018, 19:36:58
    Author     : rojo
--%>

<%@page import="java.util.List"%>
<%@page import="Dominio.Referencia"%>
<%@page import="Dominio.Vino"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%ArrayList<Vino> vinosSeleccionados = (ArrayList<Vino>) request.getAttribute("vinosSeleccionados");%>
        <%List<Referencia> referencias = (List<Referencia>) request.getSession().getAttribute("referencias");%>
        <%String nVinos = (String)request.getAttribute("nVinos");%>
        <h1>Compra de vinos</h1>
        <h1>Numero de vinos: <%=nVinos%></h1>
        
        <form action="Servlet">
            <h1>**** Carrito de la compra ****</h1>
        
            <% for(int i=0; i<vinosSeleccionados.size(); i++) { %>
                <%String nombreVino = vinosSeleccionados.get(i).getNombrecomercial();%>
                <%String descrVino = vinosSeleccionados.get(i).getComentario();%>
                <%String precioVino = String.valueOf(referencias.get(i).getPrecio());%>
                <h2><%=nombreVino%> -- <%=descrVino%> -- <%=precioVino%> €</h2>
            <% } %>
            
            <button type="submit" value="PEDIDO" name="pedido"> Realizar pedido </button>
        </form>
    </body>
</html>
