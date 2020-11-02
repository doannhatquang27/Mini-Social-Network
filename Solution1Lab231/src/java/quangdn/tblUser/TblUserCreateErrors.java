/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quangdn.tblUser;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class TblUserCreateErrors implements Serializable{
    private String loginError;
    private String emailTypeError;
    private String emailLengthError;
    private String passwordLengthError;
    private String nameLengthError;
    private String duplicateEmail;

    /**
     * @return the emailTypeError
     */
    public String getEmailTypeError() {
        return emailTypeError;
    }

    /**
     * @param emailTypeError the emailTypeError to set
     */
    public void setEmailTypeError(String emailTypeError) {
        this.emailTypeError = emailTypeError;
    }

    /**
     * @return the emailLengthError
     */
    public String getEmailLengthError() {
        return emailLengthError;
    }

    /**
     * @param emailLengthError the emailLengthError to set
     */
    public void setEmailLengthError(String emailLengthError) {
        this.emailLengthError = emailLengthError;
    }

    /**
     * @return the passwordLengthError
     */
    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    /**
     * @param passwordLengthError the passwordLengthError to set
     */
    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    /**
     * @return the nameLengthError
     */
    public String getNameLengthError() {
        return nameLengthError;
    }

    /**
     * @param nameLengthError the nameLengthError to set
     */
    public void setNameLengthError(String nameLengthError) {
        this.nameLengthError = nameLengthError;
    }

    /**
     * @return the duplicateEmail
     */
    public String getDuplicateEmail() {
        return duplicateEmail;
    }

    /**
     * @param duplicateEmail the duplicateEmail to set
     */
    public void setDuplicateEmail(String duplicateEmail) {
        this.duplicateEmail = duplicateEmail;
    }

    /**
     * @return the loginError
     */
    public String getLoginError() {
        return loginError;
    }

    /**
     * @param loginError the loginError to set
     */
    public void setLoginError(String loginError) {
        this.loginError = loginError;
    }
    
    
}
