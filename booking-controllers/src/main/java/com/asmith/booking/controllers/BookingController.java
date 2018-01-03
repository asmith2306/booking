package com.asmith.booking.controllers;

import com.asmith.booking.entities.Booking;
import com.asmith.booking.domain.beans.DomainBean;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author asmith
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    DomainBean<Booking> bookingBean;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Booking> create(@RequestBody Booking booking) {
        // create booking
        return new ResponseEntity<>(bookingBean.create(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Booking> read(@PathVariable("id") String id) {
        // return single booking
        return new ResponseEntity<>(bookingBean.find(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Booking>> readAll() {

        return new ResponseEntity<>(bookingBean.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<Booking> update(@PathVariable("id") String id, @RequestBody Booking booking) {
        // get the booking and replace it with the incoming object
        return new ResponseEntity<>(bookingBean.update(id, booking), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    ResponseEntity<Booking> patch(@PathVariable("id") String id, @RequestBody Booking booking) {
        // get the booking and patch just the field that isn't null
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id) {
        bookingBean.delete(id);
    }

}
