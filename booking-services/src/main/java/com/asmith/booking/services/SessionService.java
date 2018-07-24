package com.asmith.booking.services;

import com.asmith.booking.entities.Customer;
import com.asmith.booking.entities.CustomerSession;
import com.asmith.booking.entities.embeddables.LoginDetails;
import com.asmith.booking.repos.CustomerRepository;
import com.asmith.booking.repos.CustomerSessionRepository;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alan
 */
@Service
public class SessionService {

    private static final Logger LOG = LoggerFactory.getLogger(SessionService.class.getName());

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerSessionRepository customerSessionRepository;

    @Transactional
    public void checkSession(HttpServletRequest requestIn, HttpServletResponse responseIn) {
        HttpSession session = requestIn.getSession(false);

        if (null == session) {
            responseIn.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        List<CustomerSession> customerSessions = customerSessionRepository.findBySessionId(getJSessionId(requestIn));
        if (customerSessions.isEmpty()) {
            responseIn.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        CustomerSession customerSession = customerSessions.get(0);
        Customer customer = customerRepository.findByCustomerSession(customerSession).get(0);

        customerSession.setLastAccessed(System.currentTimeMillis());
        customerSessionRepository.save(customerSession);

        LOG.info(customer.toString());
    }

    public void createSession(LoginDetails loginDetails, HttpServletRequest request) {
        Customer customer = customerRepository.findByEmail(loginDetails.getUserName()).get(0);

        HttpSession newSession = request.getSession();
        CustomerSession customerSession = new CustomerSession();

        customerSession.setSessionId(newSession.getId());
        customerSession.setLastAccessed(System.currentTimeMillis());

        customer.setCustomerSession(customerSession);

        customerSessionRepository.save(customerSession);
        customerRepository.save(customer);
    }

    public void deleteExpiredSessions(Long sessionTimeout) {
        List<CustomerSession> expiredSessions = customerSessionRepository.findBylastAccessedLessThan(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(sessionTimeout));
        expiredSessions.forEach(session -> {
            deleteSession(session);
        });
    }

    public void logout(HttpServletRequest requestIn) {
        List<CustomerSession> customerSessions = customerSessionRepository.findBySessionId(getJSessionId(requestIn));
        if (!customerSessions.isEmpty()) {
            deleteSession(customerSessions.get(0));
        }
    }

    private void deleteSession(CustomerSession session) {
        Customer customer = customerRepository.findByCustomerSession(session).get(0);
        customer.setCustomerSession(null);
        customerRepository.save(customer);
        customerSessionRepository.delete(session);
    }

    private String getJSessionId(HttpServletRequest requestIn) {
        if (null != requestIn.getCookies()) {
            for (Cookie c : requestIn.getCookies()) {
                if (c.getName().equals("JSESSIONID")) {
                    return c.getValue();
                }
            }
        }
        return null;
    }

    public boolean hasActiveSession(HttpServletRequest requestIn) {
        List<CustomerSession> customerSessions = customerSessionRepository.findBySessionId(getJSessionId(requestIn));
        return !customerSessions.isEmpty();
    }

}
