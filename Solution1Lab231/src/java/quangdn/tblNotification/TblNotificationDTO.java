/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblNotification;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblNotificationDTO implements Serializable{
    private String email; 
    private int articleId;
//    private Date date;
    private String date;
    private boolean type;

    public TblNotificationDTO() {
    }

    public TblNotificationDTO(String email, int articleId, String date, boolean type) {
        this.email = email;
        this.articleId = articleId;
        this.date = date;
        this.type = type;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the articleId
     */
    public int getArticleId() {
        return articleId;
    }

    /**
     * @param articleId the articleId to set
     */
    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

//    /**
//     * @return the date
//     */
//    public Date getDate() {
//        return date;
//    }
//
//    /**
//     * @param date the date to set
//     */
//    public void setDate(Date date) {
//        this.date = date;
//    }

    /**
     * @return the type
     */
    public boolean isType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(boolean type) {
        this.type = type;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the datetime to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
    
}
