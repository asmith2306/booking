package com.asmith.booking.entities.embeddables;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 * @author Alan
 */
@Embeddable
public class LoginDetails implements Serializable {

    //this should be the customers email
    private String userName;
    private String password;

    public LoginDetails() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "CustomerLoginDetails{" + "userName=" + userName + ", password=" + password + '}';
    }
}
