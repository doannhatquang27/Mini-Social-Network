/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblEmotion;

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
public class TblEmotionDAO implements Serializable{
    private ArrayList<TblEmotionDTO> reactList = new ArrayList<>();
    private ArrayList<TblEmotionDTO> userReactList = new ArrayList<>();
    
    public void getReactList(int articleId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT email, reactDate, reactStatus "
                    + "FROM tblEmotion WHERE articleId=?";
            
            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                String email = rs.getString(1);
                Timestamp time = rs.getTimestamp(2);
                String strDate = new SimpleDateFormat("MMM dd yyyy").format(time);
                String strTime = new SimpleDateFormat("HH:mm").format(time);
                String reactDate = "" + strDate + " at " + strTime;
                boolean reactStatus = rs.getBoolean(3);
                
                TblEmotionDTO dto = new TblEmotionDTO(email, articleId, reactDate, reactStatus);
                
                this.getReactList().add(dto);
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
    
    public int getNumOfLike(int articleId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int numOfLike = 0;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT COUNT(email) "
                    + "FROM tblEmotion WHERE articleId=? AND reactStatus=?";
            
            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            stm.setBoolean(2, true);
            rs = stm.executeQuery();
            
            if (rs.next()) {
                numOfLike = rs.getInt(1);
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
        return numOfLike;
    }  
    
    public int getNumOfDislike(int articleId) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int numOfDislike = 0;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT COUNT(email) "
                    + "FROM tblEmotion WHERE articleId=? AND reactStatus=?";
            
            stm = con.prepareStatement(query);
            stm.setInt(1, articleId);
            stm.setBoolean(2, false);
            rs = stm.executeQuery();
            
            if (rs.next()) {
                numOfDislike = rs.getInt(1);
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
        return numOfDislike;
    }  
    
    public boolean isReactBefore(String email, int articleId) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT email FROM tblEmotion WHERE email=? AND articleId=?";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setInt(2, articleId);
            
            rs = stm.executeQuery();
            if (rs.next()) {
                result = true;
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
    
    public int getCurrentReaction(String email, int articleId) 
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int result = -1;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT reactStatus FROM tblEmotion "
                    + "WHERE email=? AND articleId=? AND reactStatus IS NOT NULL";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setInt(2, articleId);
            
            rs = stm.executeQuery();
            if (rs.next()) {
                boolean status = rs.getBoolean(1);
                result = (status) ? 1 : 0; 
                
                // 1 == Like // 0 == Dislike // -1 == NULL
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
    
    public boolean updateToNull(String email, int articleId) 
            throws SQLException, NamingException {
        
        boolean isNull = false;
        Connection con = null;
        PreparedStatement stm = null;
        int result;
        
        try {
            con = new DBHelper().getConnection();
            String query = "UPDATE tblEmotion SET reactStatus=NULL "
                    + "WHERE email=? AND articleId=?";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setInt(2, articleId);
            
            result = stm.executeUpdate();
            if (result > 0) {
                isNull = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return isNull;
    }
    
    public boolean updateNewReaction(String email, int articleId, boolean status) 
            throws SQLException, NamingException {
        
        boolean isUpdated = false;
        Connection con = null;
        PreparedStatement stm = null;
        int result;
        
        try {
            con = new DBHelper().getConnection();
            String query = "UPDATE tblEmotion SET reactDate=GETDATE(), reactStatus=? "
                    + "WHERE email=? AND articleId=?";
            
            stm = con.prepareStatement(query);
            stm.setBoolean(1, status);
            stm.setString(2, email);
            stm.setInt(3, articleId);
            
            result = stm.executeUpdate();
            if (result > 0) {
                isUpdated = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return isUpdated;
    }
    
    public boolean insertNewReaction(String email, int articleId, boolean status) 
            throws SQLException, NamingException {
        
        boolean isInserted = false;
        Connection con = null;
        PreparedStatement stm = null;
        int result;
        
        try {
            con = new DBHelper().getConnection();
            String query = "INSERT INTO tblEmotion VALUES (?, ?, GETDATE(), ?)";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setInt(2, articleId);
            stm.setBoolean(3, status);
            
            result = stm.executeUpdate();
            if (result > 0) {
                isInserted = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return isInserted;
    }
    
    
    
    /**
     * @return the reactList
     */
    public ArrayList<TblEmotionDTO> getReactList() {
        return reactList;
    }

    /**
     * @param email
     * @return the userReactList
     * @throws javax.naming.NamingException
     * @throws java.sql.SQLException
     */
    public ArrayList<TblEmotionDTO> getUserReactList(String email) 
            throws NamingException, SQLException {
        this.userReactList = new ArrayList<>();
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = new DBHelper().getConnection();
            
            String query = "SELECT articleId, reactDate, reactStatus "
                    + "FROM tblEmotion WHERE email=?";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int articleId = rs.getInt(1);
                Timestamp time = rs.getTimestamp(2);
                String strDate = new SimpleDateFormat("MMM dd yyyy").format(time);
                String strTime = new SimpleDateFormat("HH:mm").format(time);
                String reactDate = "" + strDate + " at " + strTime;
                
                Object obj = rs.getObject(3);
                Boolean reactStatus = (Boolean) obj;
                TblEmotionDTO dto = new TblEmotionDTO(email, articleId, reactDate, reactStatus);
                
                this.userReactList.add(dto);
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
        return userReactList;
    }
}
