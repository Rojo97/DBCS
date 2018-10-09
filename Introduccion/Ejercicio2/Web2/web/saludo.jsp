<%-- 
    Document   : saludo
    Created on : 03-oct-2018, 19:36:58
    Author     : rojo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%String saludo = (String) request.getAttribute("nombre");%>
        <h1><%=saludo%></h1>
    </body>
</html>
