package com.spring.batch.input.itemstream.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

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
    @StepScope
    public StatefulItemReader itemReader() {
        List<String> items = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            items.add(String.valueOf(i));
        }
        return new StatefulItemReader(items);
    }

    @Bean
    public ItemWriter itemWriter() {
        return (ItemWriter<String>) items -> {
            items.forEach(item -> System.out.println(">>" + item));
        };
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(10)
                .reader(itemReader())
                .writer(itemWriter())
                .stream(itemReader())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job12")
                .start(step())
                .build();
    }
}
