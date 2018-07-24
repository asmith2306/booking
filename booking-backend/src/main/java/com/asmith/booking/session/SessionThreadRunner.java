package com.asmith.booking.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author asmith
 */
@Component
public class SessionThreadRunner implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SessionThreadRunner.class.getName());

    private final SessionThread sessionThread;

    public SessionThreadRunner(SessionThread sessionThreadIn) {
        sessionThread = sessionThreadIn;
    }

    @Override
    public void run(String... args) throws Exception {
        Thread t = new Thread(sessionThread);
        t.start();
    }

}
