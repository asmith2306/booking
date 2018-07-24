package com.asmith.booking.session;

import com.asmith.booking.services.SessionService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author asmith
 */
@Component
public class SessionThread implements Runnable {
    
    private static final Logger LOG = LoggerFactory.getLogger(SessionThread.class.getName());
    
    private boolean threadEnabled = true;
    
    @Value("${user.session.timeout}")
    private String sessionTimeout;
    
    @Value("${user.session.check.interval}")
    private String sessionCheckInterval;
    
    @Autowired
    SessionService sessionService;
    
    @Override
    public void run() {
        int i = 0;
        while (threadEnabled) {
            //LOG.info("i is: " + i++);
            try {
                sessionService.deleteExpiredSessions(Long.valueOf(sessionTimeout));
                Thread.sleep(TimeUnit.SECONDS.toMillis(Long.valueOf(sessionCheckInterval)));
            } catch (InterruptedException ex) {
                LOG.warn("Session thread has been interruped", ex);
            }
        }
    }
    
    public void setThreadEnabled(boolean enabled) {
        threadEnabled = enabled;
    }
    
}
