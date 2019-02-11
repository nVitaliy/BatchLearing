package com.spring.batch.outcome.itemwriter.configuration;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class SysOutItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) throws Exception {
        System.out.println("The size of this chant was " + items.size());
        items.forEach(item -> System.out.println(">>" + item));
    }
}
