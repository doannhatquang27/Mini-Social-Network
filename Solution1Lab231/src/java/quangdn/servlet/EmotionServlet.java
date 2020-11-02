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
import quangdn.tblEmotion.TblEmotionDAO;
import quangdn.tblNotification.TblNotificationDAO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "EmotionServlet", urlPatterns = {"/EmotionServlet"})
public class EmotionServlet extends HttpServlet {

    //log4j
    static Logger LOGGER = Logger.getLogger(EmotionServlet.class);
    //url option
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

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        int articleId = Integer.parseInt(request.getParameter("articleId"));
        int status = (request.getParameter("status").equals("LIKE")) ? 1 : 0;
        boolean result;
        String returnTo = request.getParameter("returnTo");
        String url = SEARCH_PAGE;

        try {
            TblEmotionDAO dao = new TblEmotionDAO();
            boolean isReacted = dao.isReactBefore(email, articleId);
            if (isReacted) { // check if this user reacted this article before                
                int currentStatus = dao.getCurrentReaction(email, articleId);
                if (currentStatus == status) { //user click double to like or dislike button
                    result = dao.updateToNull(email, articleId);

                    if (result) {
                        TblNotificationDAO notifyDAO = new TblNotificationDAO();
                        notifyDAO.deleteReactionNotification(email, articleId);
                    }
                } else {
                    //if user click other button then booleanStatus is this time
                    //reaction
                    boolean booleanStatus = (status == 1);
                    //if booleanStatus = true mean LIKE and opposite 
                    result = dao.updateNewReaction(email, articleId, booleanStatus);

                    if (result) {
                        TblNotificationDAO notifyDAO = new TblNotificationDAO();
                        notifyDAO.updateReactionNotification(email, articleId);
                    }
                }
            } else {
                boolean booleanStatus = (status == 1);
                result = dao.insertNewReaction(email, articleId, booleanStatus);

                if (result) {
                    TblNotificationDAO notifyDAO = new TblNotificationDAO();
                    notifyDAO.addNotification(email, articleId, -1, true);
                }
            }

            if (returnTo.equals("ShowDetail")) {
                url = ARTICLE_DETAIL_PAGE;
            }
        } catch (SQLException ex) {
            LOGGER.fatal("EmotionServlet _ SQL: " + ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal("EmotionServlet _ Naming: " + ex.getMessage());
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
