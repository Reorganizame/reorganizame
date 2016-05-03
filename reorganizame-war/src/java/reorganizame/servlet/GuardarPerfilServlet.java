/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reorganizame.ejb.UsuarioFacade;
import reorganizame.entity.Usuario;

/**
 *
 * @author David
 */
@WebServlet(name = "GuardarPerfilServlet", urlPatterns = {"/GuardarPerfilServlet"})
public class GuardarPerfilServlet extends HttpServlet {

    @EJB
    private UsuarioFacade usuarioFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensajeError = null;
        Usuario usuarioParaModificar = (Usuario) request.getSession().getAttribute("usuario");
        String nombreInsertado = request.getParameter("nombre"),
                apellidosInsertados = request.getParameter("apellidos"),
                fechaNacimientoInsertada = request.getParameter("fechaNacimiento"),
                correoInsertado = request.getParameter("correo"),
                aliasInsertado = request.getParameter("alias"),
                contrasenaAntiguaInsertada = request.getParameter("contrasenaAntigua"),
                contrasenaNuevaInsertada = request.getParameter("contrasenaNueva"),
                contrasenaNueva2Insertada = request.getParameter("contrasenaNueva2");
        if (Util.isValidEmailAddress(correoInsertado) && aliasInsertado.length() >= 4) {
            usuarioParaModificar.setNombre(nombreInsertado);
            usuarioParaModificar.setApellidos(apellidosInsertados);
            usuarioParaModificar.setFechaNacimiento(Util.fechaDesdeString(fechaNacimientoInsertada));
            usuarioParaModificar.setCorreo(correoInsertado);
            usuarioParaModificar.setAlias(aliasInsertado);
            if (!contrasenaNuevaInsertada.equals("")) {
                if (contrasenaNuevaInsertada.length() >= 4
                        && Util.hash(contrasenaAntiguaInsertada).equals(usuarioParaModificar.getContrasena())
                        && contrasenaNuevaInsertada.equals(contrasenaNueva2Insertada)) {
                    usuarioParaModificar.setContrasena(Util.hash(contrasenaNuevaInsertada));
                } else {
                    mensajeError = "Contrase&ntilde;a no cambiada: antigua incorrecta, nueva muy corta o no coincide repetida";
                }
            }
            if (mensajeError == null) {
                try {
                    this.usuarioFacade.edit(usuarioParaModificar);
                } catch (EJBException ex) {
                    mensajeError = "Nuevo alias o correo ya en uso";
                }
            }
        } else {
            mensajeError = "Correo no v&aacute;lido, alias muy corto, contrase&ntilde;a muy corta o no coincide repetida";
        }
        if (mensajeError == null) {
            request.setAttribute("mensajePerfil", "Cambios guardados con &eacute;xito");
        } else {
            // Recargamos el usuario de la BD por si ha cambiado el objeto con setters y ha fallado el update en la BD
            request.getSession().setAttribute("usuario", this.usuarioFacade.find(usuarioParaModificar.getIdUsuario()));
            request.setAttribute("mensajePerfil", mensajeError);
        }
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/CargarPerfilServlet");
        rd.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
