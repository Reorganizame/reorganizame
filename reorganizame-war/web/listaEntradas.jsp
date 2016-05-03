<%-- 
    Document   : listaEntradas
    Created on : 03-may-2016, 18:47:44
    Author     : Alejandro Reyes
--%>

<%@page import="reorganizame.entity.Entrada"%>
<%@page import="java.util.List"%>
<%@page import="reorganizame.entity.Tarea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de entradas</title>
    </head>
    <body>
        <h3> Lista de entradas </h3>
        <table border="1">
            <th>Titulo</th>
            <th>Contenido</th>
            <th>Creador</th>
            <th></th>
                <% for (Entrada e : (List<Entrada>) request.getAttribute("listaEntradas")) {%>
            <tr>
                <td> <%= e.getTitulo()%> </td>
                <td> <%= e.getContenido()%> </td>
                <td> <%= e.getCreador().getAlias()%> </td>
                <td> <a href="BorrarEntradaServlet?idEntrada=<%=e.getIdEntrada()%>&idProyecto=<%=request.getParameter("idProyecto")%>"> Borrar </a> </td>
            </tr>
            <% }%> 
        </table>
        <a href ="AnadirEntradaServlet?idProyecto=<%= request.getParameter("idProyecto")%>">A&ntilde;adir nueva entrada</a>
        <p>
            <a href="MostrarProyectoServlet?idProyecto=<%= request.getParameter("idProyecto")%>"> Volver al proyecto </a>
        </p> 
    </body>
</html>
