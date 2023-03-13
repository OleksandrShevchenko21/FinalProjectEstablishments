package com.finalprojectestablishments.finalprojectestablishments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })

public class FinalProjectEstablishmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectEstablishmentsApplication.class, args);
    }

}
