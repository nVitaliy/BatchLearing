package com.spring.batch.input.flatfilesreader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class FlatFilesReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlatFilesReaderApplication.class, args);
    }

}

