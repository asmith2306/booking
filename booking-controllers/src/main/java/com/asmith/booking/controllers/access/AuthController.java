package com.asmith.booking.controllers.access;

import com.asmith.booking.controllers.response.TextResponse;
import com.asmith.booking.entities.Customer;
import com.asmith.booking.services.SessionService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class.getName());

    @Autowired
    private SessionService sessionService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Customer> auth() {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        Customer customer = sessionService.checkSession(req, resp);

        if (resp.getStatus() != HttpStatus.OK.value()) {
            return new ResponseEntity(new TextResponse("Unauthorised"), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity(customer, HttpStatus.OK);
    }
//
//    @RequestMapping(value = "/user", method = RequestMethod.GET)
//    public ResponseEntity<Customer> user() {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        Customer customer = sessionService.getUser(req, resp);
//
//        if (resp.getStatus() != HttpStatus.OK.value()) {
//            return new ResponseEntity(new TextResponse("Unauthorised"), HttpStatus.UNAUTHORIZED);
//        }
//
//        return new ResponseEntity(customer, HttpStatus.OK);
//    }

}
