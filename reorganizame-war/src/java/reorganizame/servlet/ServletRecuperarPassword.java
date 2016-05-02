/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorganizame.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
 * @author Carlos
 */
@WebServlet(name = "ServletRecuperarPassword", urlPatterns = {"/ServletRecuperarPassword"})
public class ServletRecuperarPassword extends HttpServlet {

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
        String email = (String) request.getAttribute("email");
        boolean error = false;
        Usuario usuario = null;
        if(email!=null){
            usuario = this.usuarioFacade.findUserByEmail(email);
            if(usuario==null){
                error = true;
            }
        } else{
            error = true;
        }
        if(error){
            request.setAttribute("error", "error");
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/recuperarPassword.jsp");
            rd.forward(request, response); 
        } else{
            this.enviarMensaje(usuario);
            RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/passwordRecuperada.jsp");
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

    private void enviarMensaje(Usuario usuario) {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        try{
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("reorganiza.me@outlook.com", "Reorganiza.me"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getCorreo(), usuario.getNombre()));
            String password = this.generarPasswordAleatorio(10);
            usuario.setContrasena(password);
            msg.setSubject("Su contraseña ha sido reestablecida.\nLa nueva contraseña es: " + password);
            Transport.send(msg);
        } catch (AddressException e) {
            // ...
        }  catch (MessagingException | UnsupportedEncodingException ex) {
            Logger.getLogger(ServletRecuperarPassword.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String generarPasswordAleatorio(int len) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for(int i=0; i<len; i++){
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        } 
        return sb.toString();
    }

}
