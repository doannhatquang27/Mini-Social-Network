/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class DispatchController extends HttpServlet {    
    private final String REGISTER_SERVLET = "RegisterServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String POST_ARTICLE_PAGE = "PostArticle.jsp";
    private final String POST_ARTICLE_SERVLET = "PostArticleServlet";
    private final String NOTIFICATION_SERVLET = "NotificationServlet";
    private final String DELETE_ARTICLE_SERVLET = "DeleteArticleServlet";
    private final String COMMENT_SERVLET = "CommentServlet";
    private final String DELETE_COMMENT_SERVLET = "DeleteCommentServlet";
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
        
        String url = REGISTER_SERVLET;
        String button = request.getParameter("btnAction");
        
        try {
            if (button == null) {
                url = "Login.jsp";
            } else if (button.equals("Register")) {
                url = REGISTER_SERVLET;
            } else if (button.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (button.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (button.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (button.equals("PostNavigation")) {
                url = POST_ARTICLE_PAGE;
            } else if (button.equals("PostArticle")) {
                url = POST_ARTICLE_SERVLET;
            } else if (button.equals("Notification")) {
                url = NOTIFICATION_SERVLET;
            } else if (button.equals("Delete")) {
                url = DELETE_ARTICLE_SERVLET;
            } else if (button.equals("Comment")) {
                url = COMMENT_SERVLET;
            } else if (button.equals("DeleteComment")) {
                url = DELETE_COMMENT_SERVLET;
            }
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
