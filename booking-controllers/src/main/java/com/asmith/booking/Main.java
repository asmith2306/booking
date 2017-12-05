package com.asmith.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author asmith
 */
@SpringBootApplication(scanBasePackages = {"com.asmith.booking.dao", "com.asmith.booking.controllers",
"com.asmith.booking.domain.beans"})
@EntityScan(basePackages = "com.asmith.booking.entities")
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }

}
