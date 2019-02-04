package com.spring.batch.input.itemreader.reader;

import org.springframework.batch.item.ItemReader;

import java.util.Iterator;
import java.util.List;

public class StatelessItemReader implements ItemReader<String> {

    private final Iterator<String> data;

    public StatelessItemReader(List<String> data) {
        this.data = data.iterator();
    }

    @Override
    public String read() {
        if (this.data.hasNext()) {
            return this.data.next();
        }
        return null;
    }
}
