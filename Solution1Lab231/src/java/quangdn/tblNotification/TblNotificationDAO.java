/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblNotification;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.naming.NamingException;
import quangdn.utils.DBHelper;

/**
 *
 * @author ASUS
 */
public class TblNotificationDAO implements Serializable{
    private ArrayList<TblNotificationDTO> notificationList = new ArrayList<>();

    /**
     * @return the notificationList
     */
    public ArrayList<TblNotificationDTO> getNotificationList() {
        return notificationList;
    }
    
    public void getNotification(String email) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = new DBHelper().getConnection();
            String query = "SELECT tblNotification.email, tblNotification.articleId, date, type "
                    + "FROM tblNotification "
                    + "INNER JOIN tblArticle ON tblArticle.articleId = tblNotification.articleId "
                    + "WHERE tblArticle.email=? AND noStatus=? "
                    + "ORDER BY date DESC";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                String reacter = rs.getString(1);
                int articleId = rs.getInt(2);
                Timestamp time = rs.getTimestamp(3);
                String strDate = new SimpleDateFormat("MMM dd yyyy").format(time);
                String strTime = new SimpleDateFormat("HH:mm").format(time);
                String datetime = "" + strDate + " at " + strTime;
                Boolean type = rs.getBoolean(4);
                
                TblNotificationDTO dto = new TblNotificationDTO(reacter, articleId, datetime, type);
                notificationList.add(dto);
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
    
    public void addNotification(String email, int articleId, int ordinal, Boolean type) 
            throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = new DBHelper().getConnection();
            String query = "INSERT INTO tblNotification VALUES (?, ?, ?, GETDATE(), ?, ?)";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setInt(2, articleId);
            stm.setInt(3, ordinal);
            stm.setBoolean(4, type);
            stm.setBoolean(5, true);
            
            stm.execute();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void deleteReactionNotification(String email, int articleId) 
            throws NamingException, SQLException {
                
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "UPDATE tblNotification SET noStatus=? "
                         + "WHERE email=? AND articleId=? AND type=?";
            
            stm = con.prepareStatement(query);
            stm.setBoolean(1, false);
            stm.setString(2, email);                       
            stm.setInt(3, articleId);
            stm.setBoolean(4, true);
            
            stm.execute();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void updateReactionNotification(String email, int articleId) 
            throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "UPDATE tblNotification SET date=GETDATE(), noStatus=? "
                         + "WHERE email=? AND articleId=? AND type=?";
            
            stm = con.prepareStatement(query);
            stm.setBoolean(1, true);
            stm.setString(2, email);
            stm.setInt(3, articleId);
            stm.setBoolean(4, true);
            
            stm.execute();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
    
    public void deleteCommentNotification(String email, int articleId, int ordinal) 
            throws NamingException, SQLException {
        
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "UPDATE tblNotification SET noStatus=? "
                         + "WHERE email=? AND articleId=? AND type=? AND ordinal=?";
            
            stm = con.prepareStatement(query);
            stm.setBoolean(1, false);
            stm.setString(2, email);
            stm.setInt(3, articleId);
            stm.setBoolean(4, false );
            stm.setInt(5, ordinal);
            
            stm.execute();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
