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
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {

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
        String correoInsertado = request.getParameter("correo"),
                aliasInsertado = request.getParameter("alias"),
                contrasenaInsertada = request.getParameter("contrasena"),
                contrasena2Insertada = request.getParameter("contrasena2"),
                nombreInsertado = request.getParameter("nombre"),
                apellidosInsertados = request.getParameter("apellidos"),
                fechaNacimientoInsertada = request.getParameter("fechaNacimiento");
        if (Util.isValidEmailAddress(correoInsertado)
                && aliasInsertado.length() >= 4
                && contrasenaInsertada.length() >= 4
                && contrasenaInsertada.equals(contrasena2Insertada)) {
            Usuario usuarioParaRegistrar = new Usuario(0, correoInsertado, aliasInsertado, Util.hash(contrasenaInsertada));
            usuarioParaRegistrar.setNombre(nombreInsertado);
            usuarioParaRegistrar.setApellidos(apellidosInsertados);
            usuarioParaRegistrar.setFechaNacimiento(Util.fechaDesdeString(fechaNacimientoInsertada));
            try {
                this.usuarioFacade.create(usuarioParaRegistrar);
            } catch (EJBException ex) {
                mensajeError = "Ya existe usuario con mismo alias o correo";
            }
        } else {
            mensajeError = "Correo no v&aacute;lido, alias muy corto, contrase&ntilde;a muy corta o no coincide repetida";
        }
        if (mensajeError == null) {
            request.setAttribute("mensajeLogin", "Usuario registrado con &eacute;xito");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/login.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("nombre", nombreInsertado);
            request.setAttribute("apellidos", apellidosInsertados);
            request.setAttribute("fechaNacimiento", fechaNacimientoInsertada);
            request.setAttribute("correo", correoInsertado);
            request.setAttribute("alias", aliasInsertado);
            request.setAttribute("mensajeRegistro", mensajeError);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/registro.jsp");
            rd.forward(request, response);
        }
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
