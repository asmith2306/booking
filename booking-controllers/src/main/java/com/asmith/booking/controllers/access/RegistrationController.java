package com.asmith.booking.controllers.access;

import com.asmith.booking.entities.embeddables.RegistrationDetails;
import com.asmith.booking.services.AccessService;
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
    private AccessService accessService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody RegistrationDetails registrationDetails) {

        if (!accessService.customerExists(registrationDetails.getEmail()).isEmpty()) {
            return new ResponseEntity("Email already in use", HttpStatus.CONFLICT);
        }

        accessService.registerCustomer(registrationDetails);

        return new ResponseEntity(registrationDetails, HttpStatus.OK);
    }

}
