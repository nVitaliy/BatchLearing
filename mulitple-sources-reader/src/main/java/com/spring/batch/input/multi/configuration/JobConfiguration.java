package com.spring.batch.input.multi.configuration;

import com.spring.batch.input.multi.domain.Customer;
import com.spring.batch.input.multi.domain.CustomerFieldSetMapper;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;


@Configuration
public class JobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    @Value("classpath*:/data/customer*.csv")
    private Resource[] inputFiles;

    @Autowired
    public JobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public MultiResourceItemReader<Customer> multiResourceItemReader() {
        MultiResourceItemReader<Customer> resourceItemReader = new MultiResourceItemReader<>();
        resourceItemReader.setDelegate(customerItemReader());
        resourceItemReader.setResources(inputFiles);
        return resourceItemReader;
    }

    @Bean
    FlatFileItemReader<Customer> customerItemReader() {
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<>();
        DefaultLineMapper<Customer> customerDefaultLineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "firstName", "lastName", "birthdate");

        customerDefaultLineMapper.setLineTokenizer(tokenizer);
        customerDefaultLineMapper.setFieldSetMapper(new CustomerFieldSetMapper());
        customerDefaultLineMapper.afterPropertiesSet();

        reader.setLineMapper(customerDefaultLineMapper);
        return reader;
    }

    @Bean
    public ItemWriter<Customer> customerItemWriter() {
        return items -> items.forEach(item -> System.out.println(item.toString()));
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer>chunk(10)
                .reader(multiResourceItemReader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job job() {
        return jobBuilderFactory.get("job9")
                .start(step1())
                .build();
    }

}
