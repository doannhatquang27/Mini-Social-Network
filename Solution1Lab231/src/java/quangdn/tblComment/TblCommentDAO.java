/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblComment;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.naming.NamingException;
import quangdn.utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class TblCommentDAO implements Serializable {

    private ArrayList<TblCommentDTO> commentList = new ArrayList<>();

    public void getArticleComment(int articleId)
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = new DBHelper().getConnection();

            String query = "SELECT commentId, email, commentContent, cmtDate "
                    + "FROM tblComment WHERE articleId=? AND cmtStatus=?";

            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();

            while (rs.next()) {
                int commmentId = rs.getInt(1);
                String email = rs.getString(2);
                String commentContent = rs.getString(3);
                Timestamp time = rs.getTimestamp(4);
                String strDate = new SimpleDateFormat("MMM dd yyyy").format(time);
                String strTime = new SimpleDateFormat("HH:mm").format(time);
                String cmtDate = "" + strDate + " at " + strTime;

                TblCommentDTO dto = new TblCommentDTO(commmentId, email,
                        articleId, commentContent, cmtDate);

                this.getCommentList().add(dto);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public boolean postNewComment(int commentId, String email, int articleId,
            String content) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = new DBHelper().getConnection();
            String query = "INSERT INTO tblComment VALUES (?,?,?,?, GETDATE(),?)";

            stm = con.prepareStatement(query);
            stm.setInt(1, commentId);
            stm.setString(2, email);
            stm.setInt(3, articleId);
            stm.setString(4, content);
            stm.setBoolean(5, true);

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getCurrentCommentId() throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int id = 0;

        try {
            con = new DBHelper().getConnection();

            String query = "SELECT MAX(commentId) FROM tblComment";
            stm = con.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return id;
    }

    public int getNumberOfCommentOfActicle(int articleId) throws NamingException, SQLException {
        int count = 0;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = new DBHelper().getConnection();

            String query = "SELECT COUNT(commentId) "
                    + "FROM tblComment WHERE articleId=? AND cmtStatus=?";

            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return count;
    }

    public boolean deleteComment(int commentID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = new DBHelper().getConnection();
            String query = "UPDATE tblComment SET cmtStatus=? WHERE commentId=?";

            stm = con.prepareStatement(query);
            stm.setBoolean(1, false);
            stm.setInt(2, commentID);

            int row = stm.executeUpdate();
            if (row > 0) {
                return true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public TblCommentDTO getfirstCommentOfArticle(int articleId)
            throws NamingException, SQLException {
        TblCommentDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;

        try {
            con = new DBHelper().getConnection();

            String query = "SELECT TOP 1 commentId, email, commentContent, cmtDate "
                    + "FROM tblComment WHERE articleId=? AND cmtStatus=? "
                    + "ORDER BY cmtDate DESC";

            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();

            if (rs.next()) {
                int commentId = rs.getInt(1);
                String email = rs.getString(2);
                String content = rs.getString(3);
                Timestamp time = rs.getTimestamp(4);
                String strDate = new SimpleDateFormat("MMM dd yyyy").format(time);
                String strTime = new SimpleDateFormat("HH:mm").format(time);
                String cmtDate = "" + strDate + " at " + strTime;
                
                
                dto = new TblCommentDTO(commentId, email, articleId, content, cmtDate);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }

    /**
     * @return the commentList
     */
    public ArrayList<TblCommentDTO> getCommentList() {
        return commentList;
    }
}
