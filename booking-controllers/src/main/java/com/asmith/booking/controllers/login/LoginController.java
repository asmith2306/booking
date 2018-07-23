package com.asmith.booking.controllers.login;

import com.asmith.booking.entities.embeddables.LoginDetails;
import com.asmith.booking.services.SessionManager;

import javax.websocket.server.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    SessionManager sm;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(@RequestBody LoginDetails loginDetails) {
        LOG.info(loginDetails.toString());
        return null;
    }

}
