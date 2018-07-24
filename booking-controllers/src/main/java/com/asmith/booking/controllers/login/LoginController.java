package com.asmith.booking.controllers.login;

import com.asmith.booking.entities.embeddables.LoginDetails;
import com.asmith.booking.services.LoginRegistrationService;
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

    @Autowired
    private SessionService sessionService;

    @Autowired
    private LoginRegistrationService loginService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody LoginDetails loginDetails) {

        LOG.info(loginDetails.toString());

        // Check user name
        if (!loginService.customerExists(loginDetails.getUserName())) {
            return new ResponseEntity("We cannot find an account with that e-mail address", HttpStatus.CONFLICT);
        }

        //Check password
        if (loginService.invalidPassword(loginDetails)) {
            return new ResponseEntity("Your password is incorrect", HttpStatus.NOT_ACCEPTABLE);
        }

        sessionService.createSession(loginDetails, request);

        return new ResponseEntity("Login success", HttpStatus.OK);
    }

}
