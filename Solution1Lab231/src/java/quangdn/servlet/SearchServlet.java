/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import quangdn.tblArtical.TblArticalDAO;
import quangdn.tblArtical.TblArticalDTO;
import quangdn.tblComment.TblCommentDAO;
import quangdn.tblComment.TblCommentDTO;
import quangdn.tblEmotion.TblEmotionDAO;
import quangdn.tblEmotion.TblEmotionDTO;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    //log4j
    static Logger LOGGER = Logger.getLogger(SearchServlet.class);
    //url option
    private final String RESULT_PAGE = "search.jsp";
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
        String searchValue = request.getParameter("txtSearchValue");
        if (searchValue == null) searchValue = "";
        String url = RESULT_PAGE;
        String pageStr = request.getParameter("page");
        int page = (pageStr != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        
        try {
            TblArticalDAO dao = new TblArticalDAO();
            dao.getAllArticle(searchValue, page);
            ArrayList<TblArticalDTO> list = dao.getArticleList();
            int count = dao.getNumberOfArticle(searchValue);
            request.setAttribute("LIST", list);
            int pageCount = (count % 20 == 0) ? (count / 20): (count / 20 + 1);
            request.setAttribute("COUNT", pageCount);
            request.setAttribute("CURRENTPAGE", page);
            
            TblEmotionDAO emoDAO = new TblEmotionDAO();
            ArrayList<TblEmotionDTO> userReactList = emoDAO.getUserReactList(email);
            request.setAttribute("USERREACTLIST", userReactList);
            
            TblCommentDAO cmtDAO = new TblCommentDAO();
            ArrayList<TblCommentDTO> commentList = new ArrayList<>();
            for (TblArticalDTO dto : list) {
                TblCommentDTO firstComment = cmtDAO.getfirstCommentOfArticle(dto.getArticleId());
                if (firstComment != null) {
                    commentList.add(firstComment);
                }                
            }
            request.setAttribute("FIRSTCOMMENTLIST", commentList);
        } catch (NamingException ex) {
            LOGGER.fatal("SearchServlet _ Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.fatal("SearchServlet _ SQL: " + ex.getMessage());
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
