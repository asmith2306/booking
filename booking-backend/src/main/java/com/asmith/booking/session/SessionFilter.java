package com.asmith.booking.session;

import com.asmith.booking.services.SessionService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

public class SessionFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(SessionFilter.class.getName());

    private final SessionService sessionService;

    public SessionFilter(SessionService sessionServiceIn) {
        sessionService = sessionServiceIn;
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        sessionService.checkSession(req, resp);

        if (resp.getStatus() != HttpStatus.OK.value()) {
            resp.getOutputStream().println("Unauthorised");
            resp.getOutputStream().close();
            return;
        }
        
        

        fc.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
