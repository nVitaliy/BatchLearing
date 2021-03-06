package com.spring.decisions;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DecisionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecisionsApplication.class, args);
    }

}

