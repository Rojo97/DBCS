<%-- 
    Document   : carro
    Created on : 10-oct-2018, 10:01:21
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
        <%String carro = (String) request.getAttribute("carro");%>
        <h1><%=carro%></h1>
    </body>
</html>
