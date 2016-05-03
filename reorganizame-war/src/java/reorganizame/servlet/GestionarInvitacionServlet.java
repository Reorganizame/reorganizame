/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.servlet;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reorganizame.ejb.InvitacionFacade;
import reorganizame.ejb.MiembroFacade;
import reorganizame.entity.Invitacion;
import reorganizame.entity.Miembro;
import reorganizame.entity.Usuario;

/**
 *
 * @author David
 */
@WebServlet(name = "GestionarInvitacionServlet", urlPatterns = {"/GestionarInvitacionServlet"})
public class GestionarInvitacionServlet extends HttpServlet {

    @EJB
    private InvitacionFacade invitacionFacade;

    @EJB
    private MiembroFacade miembroFacade;

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
        try {
            Integer idInvitacion = new Integer(request.getParameter("id"));
            String gestion = request.getParameter("gestion");
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            Invitacion invitacion = this.invitacionFacade.find(idInvitacion);
            if (gestion.equals("aceptar") || gestion.equals("rechazar") || invitacion.getIdUsuario().equals(usuario)) {
                if (gestion.equals("aceptar")) {
                    Miembro miembroNuevo = new Miembro();
                    miembroNuevo.setIdUsuario(usuario);
                    miembroNuevo.setIdProyecto(invitacion.getIdProyecto());
                    miembroNuevo.setRol("Invitado");
                    this.miembroFacade.create(miembroNuevo);
                }
                this.invitacionFacade.remove(invitacion);
            } else {
                mensajeError = "Gesti&oacute;n  de invitaci&oacute;n no v&aacute;lida";
            }
        } catch (NumberFormatException ex) {
            mensajeError = "Error de formato num&eacute;rico";
        }
        if (mensajeError == null) {
            request.setAttribute("mensajePerfil", "Invitaci&oacute;n gestionada correctamente");
        } else {
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
