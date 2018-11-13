<%-- 
    Document   : abonado
    Created on : 13-nov-2018, 12:08:20
    Author     : Ismael Perez
--%>

<%@page import="Dominio.Vino"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1> Soy un abonado </h1>
        <%String login = (String) request.getSession().getAttribute("login");%>
        
        <h1>Login: <%=login%></h1>
        
        <form action="Servlet">
            <%List<Vino> vinos = (List<Vino>) request.getSession().getAttribute("vinos");%>
            <h1>**** Lista de vinos ****</h1>
        
            <% for(Vino v : vinos) { %>
                <%String nombreVino = v.getNombrecomercial();%>
                <%String descrVino = v.getComentario();%>
                <input type="checkbox" name="vino" value=<%=nombreVino%>><%=nombreVino%> -- <%=descrVino%></p>
            <% } %>
            
            <button type="submit" value="CARRITO" name="carrito"> Ver el carrito </button>
        </form>
    </body>
</html>
