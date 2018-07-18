package com.asmith.booking;

import com.asmith.booking.filters.MyFilter;
import com.asmith.booking.session.SessionManager;
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
    public FilterRegistrationBean<MyFilter> myFilter() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new MyFilter(sessionManager()));
        registrationBean.addUrlPatterns("/bookings/*");
        registrationBean.addUrlPatterns("/rooms/*");

        return registrationBean;
    }
    
    @Bean
    public SessionManager sessionManager(){
        return new SessionManager();
    }

}
