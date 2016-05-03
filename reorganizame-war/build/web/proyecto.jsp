<%-- 
    Document   : proyecto
    Created on : 02-may-2016, 16:30:01
    Author     : Alejandro Reyes
--%>

<%@page import="reorganizame.entity.Entrada"%>
<%@page import="reorganizame.entity.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="reorganizame.entity.Tarea"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Reorganizame - Proyecto: <%= request.getAttribute("nombreProyecto")%></title>
    </head>
    <body>
        <h2>Proyecto: <%= request.getAttribute("nombreProyecto")%></h2>
        <table border="1">
            <th>Lista de tareas</th>
            <th></th>
            <th></th>

            <% for (Tarea t : (List<Tarea>) request.getAttribute("listaTareas")) {%>
            <tr>
                <td><%= t.getNombre()%></td>
                <td><a href="CargarTareaServlet?id=<%= t.getIdTarea()%>"> Ver </a> </td>
                <td><a href="EliminarTareaServlet?id=<%= t.getIdTarea()%>&idProyecto=<%=request.getAttribute("idProyecto")%>"> Borrar </a> </td>
            </tr>
            <% }
            %>
        </table>
        <a href="AnadirTareaServlet?idProyecto=<%= request.getAttribute("idProyecto")%>"> A&ntilde;adir tarea </a> <br />

        <br />
        <form method="get" action ="NuevaInvitacionServlet">
            <select name="invitar">
                <% for (Usuario u : (List<Usuario>) request.getAttribute("listaUsuariosParaInvitar")) {%>
                <option value="<%= u.getIdUsuario()%>"><%= u.getAlias()%></option>
                <% }%>
            </select>
            <input type="hidden" name="idProyecto" value="<%= request.getAttribute("idProyecto")%>" />
            <% if (((Usuario) request.getSession().getAttribute("usuario")).equals((Usuario) request.getAttribute("liderProyecto"))) { %>
            <button> Invitar </button>
            <% } else { %>
            <button disabled> Invitar </button>
            <% } %>
            <% if (request.getAttribute("invitacionRealizada") != null) {%>
            <h4> <%=request.getAttribute("invitacionRealizada")%> </h4>
            <% } %>
        </form>

        <br/>
        <table border="1">
            <th>Timeline</th>
                <%  int contador = 1;
                    for (Entrada e : (List<Entrada>) request.getAttribute("listaEntradas")) {
                        if (contador <= 10) {%>
            <tr>
                <td> <%= e.getTitulo()%> </td> <!-- de momento solo he puesto el titulo -->
            </tr>
            <% contador++;
                    }
                }%>     
        </table>
        <a href="AnadirEntradaServlet?idProyecto=<%= request.getParameter("idProyecto")%>" > A&ntilde;adir</a>
        <a href="VerTodasLasEntradasServlet?idProyecto=<%= request.getParameter("idProyecto")%>" >Ver mas</a>
        <p>
            <a href="ListaProyectosServlet" > Volver a la lista de proyectos </a>
        </p>
    </body>
</html>
