package com.asmith.booking.entities;

import com.asmith.booking.entities.embeddables.LoginDetails;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author asmith
 */
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"EMAIL"})})
public class Customer implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    private String surName;

    @Column(name = "EMAIL")
    private String email;

    @OneToMany(mappedBy = "customer")
    List<Booking> bookings;
   
    @OneToOne
    private CustomerSession customerSession;
    
    private LoginDetails loginDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerSession getCustomerSession() {
        return customerSession;
    }

    public void setCustomerSession(CustomerSession customerSession) {
        this.customerSession = customerSession;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

    public void setLoginDetails(LoginDetails loginDetails) {
        this.loginDetails = loginDetails;
    }
    
}
