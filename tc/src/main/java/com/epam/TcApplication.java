package com.epam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class TcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcApplication.class, args);
    }
}
