<%-- 
    Document   : listaProyectos
    Created on : 01-may-2016, 16:56:40
    Author     : nacho
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="reorganizame.entity.Usuario" %>
<%@page import="reorganizame.entity.Proyecto" %>
<!DOCTYPE html>
<%Usuario usr = (Usuario) session.getAttribute("usuario");%>
    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Proyectos</title>
    </head>
    <body>
        <a href="perfil.jsp">Hola, <%=usr.getAlias()%></a>
        <h1>Mis proyectos</h1>
         <table>
             <tr>
                <th>Nombre</th>
                <th>Descripcion</th>
            </tr>
        <c:forEach items="${requestScope.listaMisProyectos}" var="miProyecto">
        <tr>         
            <td><a href="ServletAccederProyecto?idProyecto=${miProyecto.getIdProyecto()}">${miProyecto.getNombre()}</a></td>  
            <td> ${miProyecto.getDescripcion()}</td>
        </tr>    
        </c:forEach>
        </table>
        
        <h1>Proyectos en los que participo</h1>
        
         <table>
              <tr>
                <th>Nombre</th>      
                <th>Descripcion</th>
            </tr>
        <c:forEach items="${requestScope.listaProyectos}" var="proyecto">
        <tr>         
            <td><a href="ServletAccederProyecto?idProyecto=${proyecto.getIdProyecto()}">${proyecto.getNombre()}</a></td>
            <td> ${proyecto.getDescripcion()}</td>
        </tr>    
        </c:forEach>
        </table>
        <a href="crearNuevoProyecto.jsp">Nuevo proyecto</a>
    </body>
</html>
