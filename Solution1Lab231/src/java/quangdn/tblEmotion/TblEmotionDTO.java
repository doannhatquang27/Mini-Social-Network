/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblEmotion;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblEmotionDTO implements Serializable{
    private String email; 
    private int articleId;
    private String reactDate;
    private Boolean reactStatus;

    public TblEmotionDTO() {
    }

    public TblEmotionDTO(String email, int articleId, String reactDate, Boolean reactStatus) {
        this.email = email;
        this.articleId = articleId;
        this.reactDate = reactDate;
        this.reactStatus = reactStatus;
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

    /**
     * @return the reactDate
     */
    public String getReactDate() {
        return reactDate;
    }

    /**
     * @param reactDate the reactDate to set
     */
    public void setReactDate(String reactDate) {
        this.reactDate = reactDate;
    }

    /**
     * @return the reactStatus
     */
    public Boolean getReactStatus() {
        return reactStatus;
    }

    /**
     * @param reactStatus the reactStatus to set
     */
    public void setReactStatus(Boolean reactStatus) {
        this.reactStatus = reactStatus;
    }

    
    
    
}
