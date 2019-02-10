package com.spring.batch.input.itemstream.configuration;

import org.springframework.batch.item.*;

import java.util.List;

public class StatefulItemReader implements ItemStreamReader<String> {

    private final List<String> items;
    private int curIndex = -1;
    private boolean restart = false;

    public StatefulItemReader(List<String> items) {
        this.items = items;
        this.curIndex = 0;
    }

    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        String item = null;
        if (this.curIndex < this.items.size()) {
            item = this.items.get(this.curIndex);
            this.curIndex++;
        }

        if (this.curIndex == 42 && !restart) {
            throw new RuntimeException("The Answer to the Ultimate Question of Life");
        }

        return item;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        if (executionContext.containsKey("curIndex")) {
            this.curIndex = executionContext.getInt("curIndex");
            this.restart = true;
        } else {
            this.curIndex = 0;
            executionContext.put("curIndex", curIndex);
        }

    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        executionContext.put("curIndex", this.curIndex);

    }

    @Override
    public void close() throws ItemStreamException {

    }
}
