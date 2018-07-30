package com.asmith.booking.entities.embeddables;

/**
 * @author asmith
 */
public class RegistrationDetails extends CustomerDetails {

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "RegistrationDetails{" + "email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", password=" + password + '}';
    }

}
