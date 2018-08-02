package com.asmith.booking.services;

import com.asmith.booking.entities.Customer;
import com.asmith.booking.entities.CustomerSession;
import com.asmith.booking.entities.embeddables.CustomerDetails;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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

    @Value("${user.session.timeout}")
    private String sessionTimeout;

    public Customer checkSession(HttpServletRequest requestIn, HttpServletResponse responseIn) {
        HttpSession session = requestIn.getSession(false);

        if (null == session) {
            responseIn.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

        List<CustomerSession> customerSessions = customerSessionRepository.findBySessionId(getJSessionId(requestIn));
        if (customerSessions.isEmpty()) {
            responseIn.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

        CustomerSession customerSession = customerSessions.get(0);
        if (customerSession.getLastAccessed() < (System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(Long.valueOf(sessionTimeout)))) {
            responseIn.setStatus(HttpStatus.UNAUTHORIZED.value());
            deleteSession(customerSession);
            return null;
        }

        //requestIn.setAttribute("customer", customerRepository.findByCustomerSession(customerSession).get(0));
        customerSession.setLastAccessed(System.currentTimeMillis());
        customerSessionRepository.save(customerSession);

        return customerRepository.findByCustomerSession(customerSession).get(0);
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

    public void deleteSession(CustomerSession session) {
        Customer customer = customerRepository.findByCustomerSession(session).get(0);
        customer.setCustomerSession(null);
        customerRepository.save(customer);
        customerSessionRepository.delete(session);
    }

    public void deleteSession(HttpServletRequest requestIn) {
        CustomerSession customerSession = getCustomerSession(requestIn);
        Customer customer = customerRepository.findByCustomerSession(customerSession).get(0);
        customer.setCustomerSession(null);
        customerRepository.save(customer);
        customerSessionRepository.delete(customerSession);
    }

    public boolean hasActiveSession(HttpServletRequest requestIn) {
        List<CustomerSession> customerSessions = customerSessionRepository.findBySessionId(getJSessionId(requestIn));
        return !customerSessions.isEmpty() && customerSessions.get(0).getSessionId().equals(getJSessionId(requestIn));
    }

    public Customer getUser(HttpServletRequest requestIn, HttpServletResponse responseIn) {
        CustomerSession customerSession = getCustomerSession(requestIn);

        if (null == customerSession) {
            responseIn.setStatus(HttpStatus.UNAUTHORIZED.value());
            return null;
        }

        return customerRepository.findByCustomerSession(customerSession).get(0);
    }

    private CustomerSession getCustomerSession(HttpServletRequest requestIn) {
        List<CustomerSession> customerSessions = customerSessionRepository.findBySessionId(getJSessionId(requestIn));
        if (customerSessions.isEmpty()) {
            return null;
        }
        return customerSessions.get(0);
    }

    private String getJSessionId(HttpServletRequest requestIn) {
        if (null != requestIn.getCookies()) {
            for (Cookie c : requestIn.getCookies()) {
                if (c.getName().equals("JSESSIONID")) {
                    return c.getValue();
                }
            }
        }
        return "";
    }

}
