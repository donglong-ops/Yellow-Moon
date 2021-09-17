/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.registration;

import java.io.Serializable;

/**
 *
 * @author Dong Long
 */
public class RegistrationCreateError implements Serializable {

    private String emailLengthError;
    private String passwordLengthError;
    private String confirmNotMatched;
    private String fullnameLengthError;
    private String addressLengthError;
    private String phoneLengthError;

    public RegistrationCreateError() {

    }

    public RegistrationCreateError(String emailLengthError, String passwordLengthError, String confirmNotMatched, String fullnameLengthError, String addressLengthError, String phoneLengthError) {
        this.emailLengthError = emailLengthError;
        this.passwordLengthError = passwordLengthError;
        this.confirmNotMatched = confirmNotMatched;
        this.fullnameLengthError = fullnameLengthError;
        this.addressLengthError = addressLengthError;
        this.phoneLengthError = phoneLengthError;
    }

    public String getEmailLengthError() {
        return emailLengthError;
    }

    public void setEmailLengthError(String emailLengthError) {
        this.emailLengthError = emailLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getAddressLengthError() {
        return addressLengthError;
    }

    public void setAddressLengthError(String addressLengthError) {
        this.addressLengthError = addressLengthError;
    }

    public String getPhoneLengthError() {
        return phoneLengthError;
    }

    public void setPhoneLengthError(String phoneLengthError) {
        this.phoneLengthError = phoneLengthError;
    }

}
