<%-- 
    Document   : modificaPedido
    Created on : 13-nov-2018, 22:16:27
    Author     : Ismael Perez
--%>

<%@page import="controlador.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Modificar pedido</h1>
        <%Pedido p = (Pedido)request.getSession().getAttribute("pedidoSeleccionado");%>
        <%String nombre = (String)request.getAttribute("nombrePedido");%>
        
        <h1>ID: <%=nombre%></h1>
        
        <%String idPedido = String.valueOf(p.getPeId());%>
        <%String nifPedido = p.getPeNif();%>
        <%String fechaPedido = p.getFecha();%>
        <%String precioPedido = String.valueOf(p.getImporte());%>
        <%String referenciaPedido = String.valueOf(p.getPeReferencia());%>
        <%String estadoPedido = p.getPeEstado().getEstado();%>
        
        <label>ID: <%=idPedido%> NIF: <%=nifPedido%> Fecha: <%=fechaPedido%> Importe: <%=precioPedido%> Ref: <%=referenciaPedido%>
            Estado: <%=estadoPedido%></label>
            
            <h2>Elija el nuevo estado</h2>
            <form>
                <input type="radio" name="estadoPedido" value=pendiente>Pendiente</p>
                <input type="radio" name="estadoPedido" value=tramitado>Tramitado</p>
                <input type="radio" name="estadoPedido" value=completado>Completado</p>
                
                <button type="submit" value="EDITESTADO" name="editEstadoPedido"> Editar estado del pedido </button>
                <button type="submit" value="EDITPEDIDOS" name="editPedidos"> Cancelar </button>
                <button type="submit" value="CERRARSESION" name="cerrarSesion"> CerrarSesion </button>
            </form>
    </body>
</html>
