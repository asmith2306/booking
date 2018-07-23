package com.asmith.booking.services;

import com.asmith.booking.repos.CustomerRepository;
import com.asmith.booking.repos.RoomRepository;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Alan
 */
@Service
public class SessionManager {
  
    private static final Logger LOG = LoggerFactory.getLogger(SessionManager.class.getName());

    public void checkSession(HttpServletRequest requestIn, HttpServletResponse responseIn) {

        HttpSession session = requestIn.getSession(false);

        if (null == session) {   
            responseIn.setStatus(HttpStatus.UNAUTHORIZED.value());
        }

        if (null != requestIn.getCookies()) {
            for (Cookie c : requestIn.getCookies()) {
                LOG.info(c.getName());
                LOG.info(c.getValue());
            }
        }
        
    }

}
