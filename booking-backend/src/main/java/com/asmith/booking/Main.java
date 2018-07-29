package com.asmith.booking;

import com.asmith.booking.session.SessionFilter;
import com.asmith.booking.services.SessionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author asmith
 */
@SpringBootApplication(scanBasePackages = {
    "com.asmith.booking.controllers",
    "com.asmith.booking.services"}
)
@EntityScan(basePackages = "com.asmith.booking.entities")
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilter() {
        FilterRegistrationBean<SessionFilter> filterRegistrationBean = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new SessionFilter(sessionManager()));
        filterRegistrationBean.addUrlPatterns("/api/*");

        return filterRegistrationBean;
    }

    @Bean
    public SessionService sessionManager() {
        return new SessionService();
    }

}
