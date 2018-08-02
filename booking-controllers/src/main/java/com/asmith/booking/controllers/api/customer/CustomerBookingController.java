/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asmith.booking.controllers.api.customer;

import com.asmith.booking.entities.Booking;
import com.asmith.booking.services.CustomerService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author asmith
 */
@RestController
@RequestMapping("/api/customers/{customerId}/bookings")
public class CustomerBookingController {

    @Autowired
    CustomerService customerService;

    @GetMapping()
    public List<Booking> list(@PathVariable String customerId) {
        return customerService.findAllBookings(customerId);
    }

    @GetMapping("/{id}")
    public Booking get(@PathVariable String customerId, @PathVariable String id) {
        return this.customerService.findBooking(customerId, id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> put(@PathVariable String customerId, @PathVariable String id, @RequestBody Booking bookingIn) {
        return new ResponseEntity<>(this.customerService.updateBooking(customerId, id, bookingIn), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Booking> post(@PathVariable String customerId, @RequestBody Booking input) {
        return new ResponseEntity<>(this.customerService.createBooking(customerId), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String customerId, @PathVariable String id) {
        customerService.deleteBooking(customerId, id);
    }

}
