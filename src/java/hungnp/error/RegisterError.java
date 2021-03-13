/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnp.error;

/**
 *
 * @author Win 10
 */
public class RegisterError {
    private String emailEmptyError;
    private String emailInvalidError;
    private String emailLengthError;
    private String emailExistedError;
    private String nameEmptyError;
    private String passwordEmptyError;
    private String confirmNotMatchError;

    public RegisterError() {
    }

    public RegisterError(String emailEmptyError, String emailInvalidError, String emailLengthError, String emailExistedError, String nameEmptyError, String passwordEmptyError, String confirmNotMatchError) {
        this.emailEmptyError = emailEmptyError;
        this.emailInvalidError = emailInvalidError;
        this.emailLengthError = emailLengthError;
        this.emailExistedError = emailExistedError;
        this.nameEmptyError = nameEmptyError;
        this.passwordEmptyError = passwordEmptyError;
        this.confirmNotMatchError = confirmNotMatchError;
    }

    public String getEmailEmptyError() {
        return emailEmptyError;
    }

    public void setEmailEmptyError(String emailEmptyError) {
        this.emailEmptyError = emailEmptyError;
    }

    public String getEmailInvalidError() {
        return emailInvalidError;
    }

    public void setEmailInvalidError(String emailInvalidError) {
        this.emailInvalidError = emailInvalidError;
    }

    public String getEmailLengthError() {
        return emailLengthError;
    }

    public void setEmailLengthError(String emailLengthError) {
        this.emailLengthError = emailLengthError;
    }

    public String getEmailExistedError() {
        return emailExistedError;
    }

    public void setEmailExistedError(String emailExistedError) {
        this.emailExistedError = emailExistedError;
    }

    public String getNameEmptyError() {
        return nameEmptyError;
    }

    public void setNameEmptyError(String nameEmptyError) {
        this.nameEmptyError = nameEmptyError;
    }

    public String getPasswordEmptyError() {
        return passwordEmptyError;
    }

    public void setPasswordEmptyError(String passwordEmptyError) {
        this.passwordEmptyError = passwordEmptyError;
    }

    public String getConfirmNotMatchError() {
        return confirmNotMatchError;
    }

    public void setConfirmNotMatchError(String confirmNotMatchError) {
        this.confirmNotMatchError = confirmNotMatchError;
    }

    
    
}
















