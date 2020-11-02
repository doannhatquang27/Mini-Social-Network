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
import java.util.regex.Pattern;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import quangdn.tblUser.TblUserCreateErrors;
import quangdn.tblUser.TblUserDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    //log4j
    static Logger LOGGER = Logger.getLogger(RegisterServlet.class);
    //url option
    private final String LOGIN_PAGE = "Login.jsp";
    private final String ERROR_PAGE = "Register.jsp";
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
        String name = request.getParameter("txtName");
        String password = request.getParameter("txtPassword");
        TblUserCreateErrors errors = new TblUserCreateErrors();
        boolean hasError = false;
        String url = ERROR_PAGE;

        try {
            if (email == null) {
                errors.setEmailLengthError("Email must be entered");
                hasError = true;
            } else {
                boolean isValidEmail = Pattern.matches("^[A-Za-z0-9+_.]+@(.+)$", email);
                if (!isValidEmail) {
                    errors.setEmailTypeError("Email type is wrong");
                    hasError = true;
                }
            }

            if (password.trim().length() < 6 || password.length() > 20) {
                errors.setPasswordLengthError("Password length must be 6-20 characters");
                hasError = true;
            }

            if (name.trim().length() == 0) {
                errors.setNameLengthError("Full name must be entered");
                hasError = true;
            }

            if (!hasError) {
                TblUserDAO dao = new TblUserDAO();
                boolean result = dao.registerAccount(email, name, password);
                if (result) {
                    url = LOGIN_PAGE;
                }
            } else {
                request.setAttribute("ERRORS", errors);
            }
        } catch (NamingException ex) {
            LOGGER.fatal("RegisterServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.fatal("RegisterServlet _ SQL: " + ex.getMessage());
            String msg = ex.getMessage();
            if (msg.contains("duplicate")) {
                errors.setDuplicateEmail("Already has this email");
                request.setAttribute("ERRORS", errors);
            }
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.fatal("RegisterServlet _ NoSuchAlgorithm: " + ex.getMessage());
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
