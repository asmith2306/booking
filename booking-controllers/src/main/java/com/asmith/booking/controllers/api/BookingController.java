package com.asmith.booking.controllers.api;

import com.asmith.booking.entities.Booking;
import com.asmith.booking.services.BookingService;
import com.asmith.booking.services.DomainService;
import com.asmith.booking.services.SessionService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Autowired
    private BookingService bookingBean;

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<Booking> create(@RequestBody Booking booking) {
        return new ResponseEntity<>(bookingBean.create(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Booking> read(@PathVariable("id") String id) {
        return new ResponseEntity<>(bookingBean.find(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<Booking>> readAll() {
        return new ResponseEntity<>(bookingBean.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity<Booking> update(@PathVariable("id") String id, @RequestBody Booking booking) {
        return new ResponseEntity<>(bookingBean.update(id, booking), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") String id) {
        bookingBean.delete(id);
    }

}
