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
import reorganizame.ejb.EntradaFacade;
import reorganizame.ejb.ProyectoFacade;
import reorganizame.entity.Entrada;
import reorganizame.entity.Proyecto;
import reorganizame.entity.Usuario;

/**
 *
 * @author Alejandro Reyes
 */
@WebServlet(name = "FinalizarAnadirEntradaServlet", urlPatterns = {"/FinalizarAnadirEntradaServlet"})
public class FinalizarAnadirEntradaServlet extends HttpServlet {

    @EJB
    private EntradaFacade entradaFacade;

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
        Usuario creador = (Usuario) request.getSession().getAttribute("usuario");
        Proyecto proyecto = (Proyecto) proyectoFacade.find(Integer.valueOf(request.getParameter("idProyecto")));
        String titulo = request.getParameter("titulo");
        String contenido = request.getParameter("contenido");
        Date fecha = Date.from(Instant.now());

        Entrada entrada = new Entrada(0, fecha);
        entrada.setTitulo(titulo);
        entrada.setContenido(contenido);
        entrada.setCreador(creador);
        entrada.setProyecto(proyecto);

        entradaFacade.create(entrada);

        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/VerTodasLasEntradasServlet");
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
