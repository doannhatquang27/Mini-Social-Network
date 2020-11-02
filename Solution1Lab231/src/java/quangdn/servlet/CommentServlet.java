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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quangdn.tblComment.TblCommentDAO;
import quangdn.tblNotification.TblNotificationDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "CommentServlet", urlPatterns = {"/CommentServlet"})
public class CommentServlet extends HttpServlet {
    static Logger LOGGER = Logger.getLogger(CommentServlet.class);
    
    private final String ARTICLE_DETAIL_PAGE = "ViewDetailServlet";
    private final String SEARCH_PAGE = "SearchServlet";
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
        
        String url = SEARCH_PAGE;
        
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        String content = request.getParameter("txtContent");
        String returnTo = request.getParameter("returnTo");
        
        try {
            TblCommentDAO dao = new TblCommentDAO();
            int commentId = dao.getCurrentCommentId() + 1;
            
            boolean isPosted = dao.postNewComment(commentId, email, articleId, content);
            if (isPosted) {
                TblNotificationDAO notifyDAO = new TblNotificationDAO();
                notifyDAO.addNotification(email, articleId, commentId, false);
            }
            
            if (returnTo.equals("ShowDetail")) {
                url = ARTICLE_DETAIL_PAGE;
            }
        } catch (NamingException ex) {
            LOGGER.fatal("CommentServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.fatal("CommentServlet _ SQL: " + ex.getMessage());
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
