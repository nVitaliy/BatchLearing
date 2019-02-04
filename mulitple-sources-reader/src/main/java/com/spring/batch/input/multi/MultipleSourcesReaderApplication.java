package com.spring.batch.input.multi;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class MultipleSourcesReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultipleSourcesReaderApplication.class, args);
    }

}

