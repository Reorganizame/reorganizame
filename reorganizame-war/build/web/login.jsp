<%-- 
    Document   : login
    Created on : 01-may-2016, 16:48:14
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Reorganizame - Login </title>
    </head>
    <body>
        <h1> Login </h1>
        <%
            String mensajeLogin = (String) request.getAttribute("mensajeLogin");
            if (mensajeLogin != null) {
        %>
        <%= mensajeLogin%> <br />
        <%
            }
        %>
        <form action="LoginServlet" method="post">
            Alias: <input type="text" name="alias" /> <br />
            Contrase&ntilde;a : <input type="password" name="contrasena" /> <br />
            <input type="submit" value="Iniciar sesi&oacute;n" />
        </form>
        <a href="registro.jsp"> Registrarse </a> <br />
        <a href="recuperarPassword.jsp"> Recuperar password </a>
    </body>
</html>
