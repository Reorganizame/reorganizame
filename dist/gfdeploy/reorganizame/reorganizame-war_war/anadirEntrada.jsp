<%-- 
    Document   : anadirEntrada
    Created on : 03-may-2016, 20:07:08
    Author     : Alejandro Reyes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>A&ntilde;adir nueva entrada</title>
    </head>
    <body>
        <h3>Nueva entrada</h3>
        <form name="nuevaEntrada" action="FinalizarAnadirEntradaServlet" method="GET" >
            <table border="1">
                <tr>
                    <td>Titulo</td>
                    <td> <input type="text" name="titulo" value="" maxlength="100" /> <br /> </td>
                </tr>
                <tr>
                    <td>Descripcion</td>
                    <td> <input type="text" name="contenido" value="" maxlength="750"/> <br/> </td>
                </tr>
                <tr>
                    <td><input type="hidden" name="idProyecto" value="<%= request.getParameter("idProyecto")%>" /> <br/></td>
                    <td><button> Enviar </button></td>
                </tr>

            </table>
        </form>
                    <p>
                        <a href="MostrarProyectoServlet?idProyecto=<%= request.getParameter("idProyecto")%>" > Cancelar </a>
                    </p>
    </body>
</html>
