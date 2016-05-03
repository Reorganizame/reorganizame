/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.servlet;

import java.io.IOException;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import reorganizame.ejb.MiembroFacade;
import reorganizame.ejb.ProyectoFacade;
import reorganizame.entity.Miembro;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Usuario;

/**
 *
 * @author nacho
 */
@WebServlet(urlPatterns = {"/CrearProyectoServlet"})
public class CrearProyectoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @EJB
    private ProyectoFacade facade;
    @EJB
    private MiembroFacade mfacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        Date fecha = new Date();
        Integer id = 0;

        HttpSession session = request.getSession();
        Usuario usr = (Usuario) session.getAttribute("usuario");

        Proyecto p = new Proyecto(id, fecha);
        p.setNombre(nombre);
        p.setDescripcion(descripcion);
        p.setLider(usr);

        facade.create(p);

        Miembro m = new Miembro(id);
        m.setIdUsuario(usr);
        m.setIdProyecto(p);
        m.setRol("Director de proyecto");

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ListaProyectosServlet");
        dispatcher.forward(request, response);

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
