package com.spring.batch.input.xmlreader;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class XmlReaderApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmlReaderApplication.class, args);
    }

}

