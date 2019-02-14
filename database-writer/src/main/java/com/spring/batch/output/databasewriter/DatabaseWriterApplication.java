package com.spring.batch.output.databasewriter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DatabaseWriterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseWriterApplication.class, args);
    }

}

