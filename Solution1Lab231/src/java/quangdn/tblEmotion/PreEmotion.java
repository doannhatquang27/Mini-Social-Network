/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblEmotion;

import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author ASUS
 */
public class PreEmotion implements Serializable{
    private String email;
    private int articleId;
    private int status;

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param articleId the articleId to set
     */
    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    /**
     * @return the status
     * @throws java.sql.SQLException
     * @throws javax.naming.NamingException
     */
    public int getStatus() throws SQLException, NamingException {
        TblEmotionDAO dao = new TblEmotionDAO();
        this.status = dao.getCurrentReaction(email, articleId);
        return status;
    }
}
