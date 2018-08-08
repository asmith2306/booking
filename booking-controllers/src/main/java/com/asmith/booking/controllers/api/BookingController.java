package com.asmith.booking.controllers.api;

import com.asmith.booking.entities.Booking;
import com.asmith.booking.services.BookingService;
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
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Booking> create(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.create(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Booking> find(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookingService.read(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    ResponseEntity<List<Booking>> findAll() {
        return new ResponseEntity<>(bookingService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<Booking> update(@PathVariable("id") String id, @RequestBody Booking booking) {
        return new ResponseEntity<>(bookingService.update(id, booking), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id) {
        bookingService.delete(id);
    }

}
