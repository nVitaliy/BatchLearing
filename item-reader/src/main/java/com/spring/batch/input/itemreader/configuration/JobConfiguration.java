package com.spring.batch.input.itemreader.configuration;

import com.spring.batch.input.itemreader.reader.StatelessItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public StatelessItemReader statelessItemReader() {
        return new StatelessItemReader(Arrays.asList("Foo", "Bar", "Baz"));
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(2)
                .reader(statelessItemReader())
                .writer(list -> list.forEach(item -> System.out.println("curItem = " + item)))
                .build();
    }

    @Bean
    public Job interfaceJob() {
        return jobBuilderFactory.get("interfaceJob")
                .start(step1())
                .build();
    }
}

