/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quangdn.tblArtical.TblArticalDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "PostArticleServlet", urlPatterns = {"/PostArticleServlet"})
public class PostArticleServlet extends HttpServlet {

    //log4j
    static Logger LOGGER = Logger.getLogger(PostArticleServlet.class);
    //url option
    private final String DISPLAY_ARTICLE_SERVLET = "SearchServlet";

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
        String url = DISPLAY_ARTICLE_SERVLET;

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        String title = request.getParameter("txtTitle");
        String description = request.getParameter("txtDescription");
        String fileName = request.getParameter("imageFile");

        try {
            if (title.length() != 0 && description.length() != 0) {
                TblArticalDAO dao = new TblArticalDAO();
                int articleId = dao.getCurrentArticleId() + 1;

                dao.postNewArticle(articleId, email, title, description, fileName);
            }
        } catch (NamingException ex) {
            LOGGER.fatal("PostArticleServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.fatal("PostArticleServlet _ SQL: " + ex.getMessage());
        } finally {
            response.sendRedirect(url);
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
