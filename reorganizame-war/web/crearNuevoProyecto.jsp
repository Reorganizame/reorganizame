<%-- 
    Document   : crearNuevoProyecto
    Created on : 01-may-2016, 18:50:02
    Author     : nacho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>    
   <title>Crear nuevo proyecto</title>
  </head>
  <body>
	<form name="nuevoProyecto" action="CrearProyectoServlet" method="post">
		<table border="1">
			<tbody>
				<tr>
					<td><b>Nombre:</b></td>
					<td><input type="text" name="nombre" value=""/></td>		
					</tr><tr>		
					<td><b>Descripicion:</b></td>	
					<td><input type="text" name="descripcion" value=""/></td>
					</tr><tr>
					<td colspan="2"><input type="submit" name="btnEnviar" value="Enviar"/></td>
				</tr>
			</tbody>
		</table>
	</form>
  </body>
</html>

<!--hay un apartado ID que debe ser añadido automaticamente y un apartado fecha inicio que debe añadirse en el servlet