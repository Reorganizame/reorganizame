<%-- 
    Document   : modificacionTarea
    Created on : 03-may-2016, 16:32:54
    Author     : Lenovo
--%>

<%@page import="java.util.List"%>
<%@page import="reorganizame.entity.Categoria"%>
<%@page import="reorganizame.entity.Tarea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Modificacion Tarea</title>
    </head>
    <body>
        <h1>TAREA</h1>
        <%
            Tarea tarea = (Tarea) request.getAttribute("tarea");
            List<Categoria> listaCategoria = (List<Categoria>) request.getAttribute("listaCategoria");
            Categoria categoriaSeleccionada = (Categoria) tarea.getCategoria();
        %>
        <form action="EdicionTareaServlet" method="GET">
            <input type="hidden" name="idTarea" value="<%=tarea.getIdTarea()%>"/>
            <input type="hidden" name="idProyecto" value="<%=tarea.getProyecto().getIdProyecto()%>"/>
            Autor: <input type="text" value="<%=tarea.getAutor().getAlias()%>" readonly="readonly" disabled> <br/>
            Titulo: <input type="text" name="nombre" value="<%=tarea.getNombre()%>" /> <br />
            Descripcion: <input type="text" name="descripcion" value="<%=tarea.getDescripcion()%>" /> <br />
            Fecha de inicio: <input type="text" value="<%= tarea.getFechaInicio()%>" readonly="readonly" disabled> <br />
            Categoria: <select name="categoria">
                <% for (Categoria cat : (List<Categoria>) request.getAttribute("listaCategoria")) {%>
                <%if (cat.equals(categoriaSeleccionada)) {
                %>
                <option value="<%= categoriaSeleccionada.getIdCategoria()%>" selected><%=categoriaSeleccionada.getNombre()%></option>
                <%} else {%>
                <option value="<%= cat.getIdCategoria()%>"><%=cat.getNombre()%></option>
                <% }%>
                <%}%>
            </select>
            <input type="submit" value="Modificar" />
        </form>
        <p>
            <a href="MostrarProyectoServlet?idProyecto=<%= tarea.getProyecto().getIdProyecto()%>" > Volver al proyecto </a>
        </p>
    </body>
</html>
