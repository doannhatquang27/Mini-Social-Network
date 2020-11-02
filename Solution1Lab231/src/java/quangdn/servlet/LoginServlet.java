/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quangdn.tblUser.TblUserCreateErrors;
import quangdn.tblUser.TblUserDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    //log4j
    static Logger LOGGER = Logger.getLogger(LoginServlet.class);
    //url option
    private final String DISPLAY_ARTICLE_SERVLET = "SearchServlet";
    private final String LOGIN_ERROR_PAGE = "Login.jsp";
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
        PrintWriter out = response.getWriter();

        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String url = LOGIN_ERROR_PAGE;

        try {
            TblUserDAO dao = new TblUserDAO();
            if (dao.checkLogin(email, password)) {
                String username = dao.getUserName(email);
                HttpSession session = request.getSession();
                session.setAttribute("USERNAME", username);
                session.setAttribute("EMAIL", email);                
                url = DISPLAY_ARTICLE_SERVLET;
            } else {
                TblUserCreateErrors error = new TblUserCreateErrors();
                error.setLoginError("Email or Password is incorrect");
                request.setAttribute("LOGINERROR", error);
            }
        } catch (NamingException ex) {
            LOGGER.fatal("LoginServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.fatal("LoginServlet _ SQL: " + ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.fatal("LoginServlet _ NoSuchAlgorithm: " + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
