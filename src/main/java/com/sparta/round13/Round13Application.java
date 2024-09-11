package com.sparta.round13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Round13Application {

    public static void main(String[] args) {
        SpringApplication.run(Round13Application.class, args);
    }

}
