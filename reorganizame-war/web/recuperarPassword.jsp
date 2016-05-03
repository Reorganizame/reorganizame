<%-- 
    Document   : recuperarPassword
    Created on : 01-may-2016, 23:23:37
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recuperar contrase&ntilde;a</title>
    </head>
    <body>
        <%
            String str = (String) request.getAttribute("error");
            String mensaje = "Introduzca su correo electrónico para enviarle sus datos sobre el inicio de sesión.";
            if (str != null && str.equals("error")) {
                mensaje = "Dirección de correo errónea, introduzca su correo de nuevo.";
            }
        %>
        <h1>Recuperar contrase&ntilde;a</h1>
        <p><%=mensaje%></p>
        <form action="ServletRecuperarPassword" method="POST">
            Email: <input type="email" placeholder="ejemplo@host.com" name="email"><br />
            <input type="submit" value="Enviar">
        </form>
    </body>
</html>
