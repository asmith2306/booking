package com.asmith.booking.filters;

import com.asmith.booking.services.SessionManager;
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

    private final SessionManager sm;

    public SessionFilter(SessionManager smIn) {
        sm = smIn;
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        LOG.info("In session filter");
        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse resp = (HttpServletResponse) sr1;
        sm.checkSession(req, resp);

        if (resp.getStatus() != HttpStatus.OK.value()) {
            return;
        }

        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
    }

}
