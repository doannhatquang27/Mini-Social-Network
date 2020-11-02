/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblArtical;

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
public class TblArticalDAO implements Serializable{
    private ArrayList<TblArticalDTO> articleList = new ArrayList<>();
    
    public void getAllArticle(String searchValue, int page) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT articleId, email, title, description, "
                    + "publishedDate, artImage FROM tblArticle "
                    + "WHERE description LIKE ? AND artStatus=? "
                    + "ORDER BY publishedDate DESC "
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
            
            stm = con.prepareStatement(query);
            stm.setString(1, "%" + searchValue + "%");
            stm.setBoolean(2, true);
            stm.setInt(3, (page - 1) * 20);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int articleId = rs.getInt(1);
                String email = rs.getString(2);
                String title = rs.getString(3);
                String description = rs.getString(4);
                Timestamp time = rs.getTimestamp(5);
                String strDate = new SimpleDateFormat("MMM dd yyyy").format(time);
                String publishedDate = "" + strDate;
                String artImage = rs.getString(6);
                
                getArticleList().add(new TblArticalDTO(articleId, email, title, 
                        description, publishedDate, artImage));
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
    
    public int getNumberOfArticle(String searchValue) throws NamingException, SQLException {
        Connection con = null;
        ResultSet rs = null;
//        Statement stm = null;
        PreparedStatement stm = null;
        int count = 0;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT COUNT(articleId) FROM tblArticle "
                         + "WHERE description LIKE ? AND artStatus=?";

            stm = con.prepareStatement(query);
            stm.setString(1, "%" + searchValue + "%");
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
    
    public TblArticalDTO getSpecificArticle(int articleId) 
            throws NamingException, SQLException {
        TblArticalDTO result = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT email, title, description, "
                    + "publishedDate, artImage FROM tblArticle "
                    + "WHERE articleId=? AND artStatus=?";
            
            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();
            
            if (rs.next()) {
                String email = rs.getString(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                Timestamp time = rs.getTimestamp(4);
                String strDate = new SimpleDateFormat("MMM dd yyyy").format(time);
                String publishedDate = "" + strDate;
                String artImage = rs.getString(5);
                
                result = new TblArticalDTO(articleId, email, title, description, 
                        publishedDate, artImage);
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
        return result;
    }
    
    public boolean postNewArticle(int articleId, String email, String title, 
            String description, String artImage) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = new DBHelper().getConnection();
            String query = "INSERT INTO tblArticle VALUES (?,?,?,?, GETDATE(), ?,?)";
            
            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            stm.setString(2, email);
            stm.setString(3, title);
            stm.setString(4, description);
            stm.setString(5, artImage);
            stm.setBoolean(6, true);
            
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
    
    public int getCurrentArticleId() throws NamingException, SQLException {
        Connection con = null;
        Statement stm = null;
        ResultSet rs = null;
        int id = 0;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT MAX(articleId) FROM tblArticle";
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
    
    public boolean deleteArticle(int articleId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = new DBHelper().getConnection();
            String query = "UPDATE tblArticle SET artStatus=? WHERE articleId=?";
            
            stm = con.prepareStatement(query);
            stm.setBoolean(1, false);
            stm.setInt(2, articleId);
            
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

    /**
     * @return the articleList
     */
    public ArrayList<TblArticalDTO> getArticleList() {
        return articleList;
    }
}
