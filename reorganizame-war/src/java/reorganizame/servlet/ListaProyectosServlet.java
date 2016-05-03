/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ListaProyectosServlet", urlPatterns = {"/ListaProyectosServlet"})
public class ListaProyectosServlet extends HttpServlet {

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

        HttpSession session = request.getSession();
        Usuario usr = (Usuario) session.getAttribute("usuario");

        List<Proyecto> misProyectos = facade.findProyectoByLider(usr);

        List<Miembro> membresia = mfacade.findMiembroByUsuario(usr);
        List<Proyecto> proyectos = new ArrayList<Proyecto>();

        for (Miembro m : membresia) {
            proyectos.add(m.getIdProyecto());
        }

        request.setAttribute("listaMisProyectos", misProyectos);
        request.setAttribute("listaProyectos", proyectos);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/listaProyectos.jsp");
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
