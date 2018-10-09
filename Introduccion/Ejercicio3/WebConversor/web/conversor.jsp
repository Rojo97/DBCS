<%-- 
    Document   : conversor
    Created on : 09-oct-2018, 21:01:38
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
        <%String millas = request.getAttribute("millas").toString();%>
        <h1><%=millas +" millas"%></h1>
    </body>
</html>
