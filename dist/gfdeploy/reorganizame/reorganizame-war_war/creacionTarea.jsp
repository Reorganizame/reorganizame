<%-- 
    Document   : creacionTarea
    Created on : 03-may-2016, 0:33:04
    Author     : Lenovo
--%>

<%@page import="reorganizame.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="reorganizame.entity.Categoria"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Creacion Tarea </title>
    </head>
    <body>
        <h1> Creacion Tarea </h1>
        <form action="FinalizarCreacionTareaServlet" method="get">
            <input type="hidden" name="idProyecto" value="<%=request.getParameter("idProyecto")%>"/>
            Titulo: <input type="text" name="titulo" value="" /> <br />
            Descripcion: <input type="text" name="descripcion" value="" /> <br />
            Fecha de inicio: <input type="date" name="fechaInicio" value="" /> <br />
            Categoria: <select name="categoria">
                <% for (Categoria cat : (List<Categoria>) request.getAttribute("listaCategoria")) {%>
                <option value="<%= cat.getIdCategoria()%>"><%=cat.getNombre()%></option>
                <%}
                %>
            </select>
            <input type="submit" value="Crear" />
        </form>
    </body>
</html>
