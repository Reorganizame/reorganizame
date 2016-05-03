/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.servlet;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import reorganizame.ejb.EntradaFacade;
import reorganizame.ejb.MiembroFacade;
import reorganizame.ejb.ProyectoFacade;
import reorganizame.ejb.TareaFacade;
import reorganizame.ejb.UsuarioFacade;
import reorganizame.entity.Tarea;
import reorganizame.entity.Entrada;
import reorganizame.entity.Miembro;
import reorganizame.entity.Usuario;

/**
 *
 * @author Alejandro Reyes
 */
@WebServlet(name = "MostrarProyectoServlet", urlPatterns = {"/MostrarProyectoServlet"})
public class MostrarProyectoServlet extends HttpServlet {

    @EJB
    private MiembroFacade miembroFacade;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private EntradaFacade entradaFacade;

    @EJB
    private TareaFacade tareaFacade;

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

        int idProyecto = Integer.valueOf(request.getParameter("idProyecto"));
        String nombreProyecto = proyectoFacade.findProyectoById(idProyecto).getNombre();
        Usuario liderProyecto = proyectoFacade.findProyectoById(idProyecto).getLider();
        //String descripcionProyecto = (String) proyectoFacade.find(request.getAttribute("idProyecto")).getDescripcion();

        List<Tarea> listaTareas = tareaFacade.tareasDeUnProyecto(idProyecto);
        List<Entrada> listaEntradas = entradaFacade.entradasDeUnProyecto(idProyecto);
        List<Usuario> listaUsuariosParaInvitar = usuarioFacade.usuariosNoMiembrosDeUnProyecto(idProyecto);
        request.setAttribute("nombreProyecto", nombreProyecto);
        request.setAttribute("liderProyecto", liderProyecto);
        request.setAttribute("idProyecto", idProyecto);
        request.setAttribute("listaTareas", listaTareas);
        request.setAttribute("listaEntradas", listaEntradas);
        request.setAttribute("listaUsuariosParaInvitar", listaUsuariosParaInvitar);

        RequestDispatcher rd;
        rd = this.getServletContext().getRequestDispatcher("/proyecto.jsp");
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
