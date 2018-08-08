package com.asmith.booking.controllers.access;

import com.asmith.booking.entities.Customer;
import com.asmith.booking.entities.embeddables.LoginDetails;
import com.asmith.booking.services.AccessService;
import com.asmith.booking.services.SessionService;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class.getName());

    private final SessionService sessionService;

    private final AccessService accessService;

    private final HttpServletRequest request;

    @Autowired
    public LoginController(SessionService sessionService, AccessService accessService, HttpServletRequest request) {
        this.sessionService = sessionService;
        this.accessService = accessService;
        this.request = request;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) {

        // Check user name
        if (accessService.customerExists(loginDetails.getUserName()).isEmpty()) {
            return new ResponseEntity("We cannot find an account with that e-mail address", HttpStatus.CONFLICT);
        }

        // Check password
        if (accessService.invalidPassword(loginDetails)) {
            return new ResponseEntity("Your password is incorrect", HttpStatus.NOT_ACCEPTABLE);
        }

        Customer customer = accessService.customerExists(loginDetails.getUserName()).get(0);

        // Check for an active session, delete any active session if an attempt to login is made
        if (null != customer.getCustomerSession()) {
            this.sessionService.deleteSession(customer.getCustomerSession());
        }

        sessionService.createSession(loginDetails, request);

        return new ResponseEntity(loginDetails, HttpStatus.OK);
    }

}
