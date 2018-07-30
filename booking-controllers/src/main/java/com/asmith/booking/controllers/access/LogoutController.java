package com.asmith.booking.controllers.access;

import com.asmith.booking.controllers.response.SuccessResponse;
import com.asmith.booking.services.SessionService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alan
 */
@RestController
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "")
    public ResponseEntity<SuccessResponse> logout() {
        sessionService.logout(request);
        return new ResponseEntity<>(new SuccessResponse("Logged out"), HttpStatus.OK);
    }
}
