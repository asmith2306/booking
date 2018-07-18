package com.asmith.booking.session;

import com.asmith.booking.repos.RoomRepository;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Alan
 */
@Component
public class SessionManager {

    @Autowired
    RoomRepository rr;
    
    private static final Logger LOG = LoggerFactory.getLogger(SessionManager.class.getName());

    public void checkSession(HttpServletRequest requestIn) {
        requestIn.getSession(true);

        LOG.info(Long.toString(rr.count()));
    }

}
