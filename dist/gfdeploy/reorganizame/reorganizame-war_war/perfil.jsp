<%-- 
    Document   : perfil
    Created on : 03-may-2016, 1:25:12
    Author     : David
--%>

<%@page import="java.util.List"%>
<%@page import="reorganizame.entity.Invitacion"%>
<%@page import="reorganizame.servlet.Util"%>
<%@page import="java.util.Date"%>
<%@page import="reorganizame.entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Reorganizame - Perfil </title>
    </head>
    <body>
        <h1> Perfil de usuario </h1>
        <%
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            String stringFechaNacimiento = "";
            Date fechaNacimiento = usuario.getFechaNacimiento();
            if (fechaNacimiento != null) {
                stringFechaNacimiento = Util.stringDesdeFecha(fechaNacimiento);

            }
            String mensajePerfil = (String) request.getAttribute("mensajePerfil");
            if (mensajePerfil != null) {
        %>
        <%= mensajePerfil%>
        <%
            }
        %>
        <form action="GuardarPerfilServlet" method="POST">
            Nombre: <input type="text" name="nombre" value="<%= usuario.getNombre()%>" /> <br />
            Apellidos: <input type="text" name="apellidos" value="<%= usuario.getApellidos()%>" /> <br />
            Fecha de nacimiento: <input type="date" name="fechaNacimiento" value="<%= stringFechaNacimiento%>" /> <br />
            Correo: <input type="text" name="correo" value="<%= usuario.getCorreo()%>" /> <br />
            Alias: <input type="text" name="alias" value="<%= usuario.getAlias()%>" /> <br />
            <h4> Cambiar contrase&ntilde;a</h4>
            Contrase&ntilde;a antigua: <input type="password" name="contrasenaAntigua" /> <br />
            Contrase&ntilde;a nueva: <input type="password" name="contrasenaNueva" /> <br />
            Repita la nueva contrase&ntilde;a: <input type="password" name="contrasenaNueva2" /> <br />
            <input type="submit" value="Guardar perfil" />
        </form>
        <h2>
            Invitaciones pendientes:
        </h2>
        <table border="1">
            <tr>
                <th> Proyecto </th> <th> L&iacute;der </th> <th colspan="2" > Gestionar </th>
            </tr>
            <%
                List<Invitacion> listaInvitaciones = (List<Invitacion>) request.getAttribute("listaInvitaciones");
                for (Invitacion invitacion : listaInvitaciones) {
            %>
            <tr>
                <td> <%= invitacion.getIdProyecto().getNombre()%> </td>
                <td> <%= invitacion.getIdProyecto().getLider().getAlias()%> </td>
                <td> <a href="GestionarInvitacionServlet?gestion=aceptar&id=<%= invitacion.getIdInvitacion()%>"> Aceptar </a>
                <td> <a href="GestionarInvitacionServlet?gestion=rechazar&id=<%= invitacion.getIdInvitacion()%>"> Rechazar </a>
            </tr>
            <%
                }
            %>
        </table>
        <p>
            <a href="ListaProyectosServlet" > Volver a la lista de proyectos </a>
        </p>
    </body>
</html>
