package com.asmith.booking.controllers.registration;

import com.asmith.booking.entities.embeddables.RegistrationDetails;
import com.asmith.booking.services.LoginRegistrationService;
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
 * @author asmith
 */
@RestController
@RequestMapping("/register")
public class RegistrationController {

    private static final Logger LOG = LoggerFactory.getLogger(RegistrationController.class.getName());

    @Autowired
    private LoginRegistrationService registrationService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<String> register(@RequestBody RegistrationDetails registrationDetails) {

        if (registrationService.customerExists(registrationDetails.getEmail())) {
            return new ResponseEntity("Email already in use", HttpStatus.CONFLICT);
        }

        registrationService.registerCustomer(registrationDetails);

        return new ResponseEntity("Registration complete", HttpStatus.OK);
    }

}
