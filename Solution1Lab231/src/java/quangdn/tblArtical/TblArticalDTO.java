/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblArtical;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblArticalDTO implements Serializable{
    private int articleId;
    private String email;
    private String title;
    private String description;
    private String publishedDate;
    private String artImage;

    public TblArticalDTO() {
    }

    public TblArticalDTO(int articleId, String email, String title, 
            String description, String publishedDate, String artImage) {
        this.articleId = articleId;
        this.email = email;
        this.title = title;
        this.description = description;
        this.publishedDate = publishedDate;
        this.artImage = artImage;
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the publishedDate
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * @param publishedDate the publishedDate to set
     */
    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * @return the artImage
     */
    public String getArtImage() {
        return artImage;
    }

    /**
     * @param artImage the artImage to set
     */
    public void setArtImage(String artImage) {
        this.artImage = artImage;
    }

    
  
    
}
