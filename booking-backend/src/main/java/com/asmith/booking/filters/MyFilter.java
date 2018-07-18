package com.asmith.booking.filters;

import com.asmith.booking.session.SessionManager;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(MyFilter.class.getName());

    private final SessionManager sm;

    public MyFilter(SessionManager smIn) {
        sm = smIn;
    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        LOG.info("In filter");

        sm.checkSession((HttpServletRequest) sr);
        fc.doFilter(sr, sr1);
    }

    @Override
    public void destroy() {
    }

}
