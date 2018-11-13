<%-- 
    Document   : pedidoModificado
    Created on : 13-nov-2018, 23:06:11
    Author     : Ismael Perez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String modificado = (String)request.getAttribute("editado");%>
        <h1>Pedido modificado: <%=modificado%></h1>
        
        <form>  
            <button type="submit" value="VEROPCIONES" name="opcionesEmpleado"> Volver a las opciones </button>
            <button type="submit" value="CERRARSESION" name="cerrarSesion"> CerrarSesion </button>
         </form>
    </body>
</html>
