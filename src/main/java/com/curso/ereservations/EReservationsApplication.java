package com.curso.ereservations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EReservationsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EReservationsApplication.class, args);
    }

}
