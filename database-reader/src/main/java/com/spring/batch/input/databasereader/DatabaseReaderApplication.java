package com.spring.batch.input.databasereader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class DatabaseReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseReaderApplication.class, args);
    }

}

