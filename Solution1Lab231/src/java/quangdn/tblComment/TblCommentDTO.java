/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblComment;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class TblCommentDTO implements Serializable{
    private int commentId;
    private String email;
    private int articleId;
    private String commentContent;
    private String cmtDate;

    public TblCommentDTO() {
    }

    public TblCommentDTO(int commentId, String email, int articleId, 
            String commentContent, String cmtDate) {
        this.commentId = commentId;
        this.email = email;
        this.articleId = articleId;
        this.commentContent = commentContent;
        this.cmtDate = cmtDate;
    }

    /**
     * @return the commentId
     */
    public int getCommentId() {
        return commentId;
    }

    /**
     * @param commentId the commentId to set
     */
    public void setCommentId(int commentId) {
        this.commentId = commentId;
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
     * @return the commentContent
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * @param commentContent the commentContent to set
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    /**
     * @return the cmtDate
     */
    public String getCmtDate() {
        return cmtDate;
    }

    /**
     * @param cmtDate the cmtDate to set
     */
    public void setCmtDate(String cmtDate) {
        this.cmtDate = cmtDate;
    }
    
    
}
