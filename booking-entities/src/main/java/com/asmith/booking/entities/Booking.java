package com.asmith.booking.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 * @author asmith
 */
@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkInDate;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date checkOutDate;

    @OneToMany(mappedBy = "booking")
    @JsonIgnoreProperties("booking")
    private List<Room> rooms;

    private Integer numberOfAdults = 1; // must have at least 1 adult!

    private Integer numberOfChildren;

    @ManyToOne
    private Customer customer;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
    
    public Integer getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(Integer numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public Integer getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(Integer numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

}
