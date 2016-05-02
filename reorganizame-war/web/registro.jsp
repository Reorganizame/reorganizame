<%-- 
    Document   : registro
    Created on : 01-may-2016, 17:03:41
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Reorganizame - Registro </title>
    </head>
    <body>
        <h1> Registro </h1>
        <%
            String nombre = "", apellidos = "", fechaNacimiento = "", correo = "", alias = "";
            if (request.getAttribute("nombre") != null) {
                nombre = (String) request.getAttribute("nombre");
                apellidos = (String) request.getAttribute("apellidos");
                fechaNacimiento = (String) request.getAttribute("fechaNacimiento");
                correo = (String) request.getAttribute("correo");
                alias = (String) request.getAttribute("alias");
            }
            String mensajeRegistro = (String) request.getAttribute("mensajeRegistro");
            if (mensajeRegistro != null) {
        %>
        <%= mensajeRegistro%> <br />
        <%
            }
        %>
        <form action="RegistroServlet" method="post">
            Nombre: <input type="text" name="nombre" value="<%= nombre%>" /> <br />
            Apellidos: <input type="text" name="apellidos" value="<%= apellidos%>" /> <br />
            Fecha de nacimiento: <input type="date" name="fechaNacimiento" value="<%= fechaNacimiento%>" /> <br />
            Correo: <input type="text" name="correo" value="<%= correo%>" /> <br />
            Alias: <input type="text" name="alias" value="<%= alias%>" /> <br />
            Contrase&ntilde;a: <input type="password" name="contrasena" /> <br />
            Repita la contrase&ntilde;a: <input type="password" name="contrasena2" /> <br />
            <input type="submit" value="Registrarse" />
        </form>
    </body>
</html>
