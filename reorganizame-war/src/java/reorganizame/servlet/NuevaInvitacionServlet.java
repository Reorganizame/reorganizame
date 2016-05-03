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
import reorganizame.ejb.ProyectoFacade;
import reorganizame.ejb.UsuarioFacade;
import reorganizame.entity.Invitacion;

/**
 *
 * @author Alejandro Reyes
 */
@WebServlet(name = "NuevaInvitacionServlet", urlPatterns = {"/NuevaInvitacionServlet"})
public class NuevaInvitacionServlet extends HttpServlet {

    @EJB
    private InvitacionFacade invitacionFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private ProyectoFacade proyectoFacade;

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
        response.setContentType("text/html;charset=UTF-8");

        int idUsuario = Integer.valueOf(request.getParameter("invitar"));
        int idProyecto = Integer.valueOf(request.getParameter("idProyecto"));
        Invitacion invitacion = new Invitacion(0);
        invitacion.setIdUsuario(usuarioFacade.find(idUsuario));
        invitacion.setIdProyecto(proyectoFacade.find(idProyecto));
        request.setAttribute("invitacionRealizada", "¡Invitaci&oacute;n realizada con &eacute;xito!");
        try {
            invitacionFacade.create(invitacion);
        } catch (Exception e) {
            request.setAttribute("invitacionRealizada", "El usuario ya ha sido invitado con anterioridad");
        }

        RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/MostrarProyectoServlet");
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
