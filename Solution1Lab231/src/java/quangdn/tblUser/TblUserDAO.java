/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblUser;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import quangdn.utils.DBHelper;
import quangdn.utils.EncryptionPassword;

/**
 *
 * @author ASUS
 */
public class TblUserDAO implements Serializable{
    public boolean registerAccount(String email, String name, String password) 
            throws NamingException, SQLException, NoSuchAlgorithmException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            String encyptedPassword = new EncryptionPassword().getSHAHash(password);
            
            con = new DBHelper().getConnection();
            String query = "INSERT INTO tblUser VALUES (?,?,?,?,?)";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, name);
            stm.setString(3, encyptedPassword);
            stm.setBoolean(4, true);
            stm.setBoolean(5, false);
            
            int result = stm.executeUpdate();
            if (result > 0) {
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
    
    public boolean checkLogin(String email, String password) 
            throws NoSuchAlgorithmException, NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            String encyptedPassword = new EncryptionPassword().getSHAHash(password);
            
            con = new DBHelper().getConnection();
            String query = "SELECT email, status_isActivated "
                    + "FROM tblUser WHERE email=? AND password=?";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            stm.setString(2, encyptedPassword);
            
            rs = stm.executeQuery();
            if (rs.next()) {
                boolean isActivated = rs.getBoolean(2);
                if (isActivated) return true;
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
        return false;
    }
    
    public String getUserName(String email) 
            throws NoSuchAlgorithmException, NamingException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        String username;
        
        try {            
            con = new DBHelper().getConnection();
            String query = "SELECT name "
                    + "FROM tblUser WHERE email=?";
            
            stm = con.prepareStatement(query);
            stm.setString(1, email);
            
            rs = stm.executeQuery();
            if (rs.next()) {
                username = rs.getString(1);
                return username;
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
        return null;
    }
}
