/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.servlet;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import reorganizame.ejb.CategoriaFacade;
import reorganizame.ejb.ProyectoFacade;
import reorganizame.ejb.TareaFacade;
import reorganizame.entity.Categoria;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Tarea;
import reorganizame.entity.Usuario;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "FinalizarCreacionTareaServlet", urlPatterns = {"/FinalizarCreacionTareaServlet"})
public class FinalizarCreacionTareaServlet extends HttpServlet {

    @EJB
    private TareaFacade tareaFacade;
    @EJB
    private CategoriaFacade categoriaFacade;
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

        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        Date fechaInicio = Util.fechaDesdeString(request.getParameter("fechaInicio"));
        Proyecto pr = proyectoFacade.find(Integer.parseInt(request.getParameter("idProyecto")));
        Categoria cat = categoriaFacade.find(Integer.parseInt(request.getParameter("categoria")));

        HttpSession session = request.getSession();
        Usuario usr = (Usuario) session.getAttribute("usuario");

        if (fechaInicio == null) {
            fechaInicio = Date.from(Instant.now());
        }
        Tarea tarea = new Tarea(0, fechaInicio);
        tarea.setDescripcion(descripcion);
        tarea.setNombre(titulo);
        tarea.setCategoria(cat);
        tarea.setAutor(usr);
        tarea.setProyecto(pr);

        tareaFacade.create(tarea);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MostrarProyectoServlet");
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
