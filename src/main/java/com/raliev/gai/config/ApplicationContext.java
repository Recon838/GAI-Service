package com.raliev.gai.config;

import com.raliev.gai.entity.RegNumber;
import com.raliev.gai.service.AbstractRegNumberGenerationService;
import com.raliev.gai.service.RandomRegNumberGenerationService;
import com.raliev.gai.service.SequentialRegNumberGenerationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@ComponentScan(basePackages = "com.raliev.gai")
public class ApplicationContext {

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public AtomicInteger triesCount() {
        return new AtomicInteger();
    }

    @Bean
    public Set<RegNumber> previousRegNumbers() {
        return new CopyOnWriteArraySet<>();
    }

    @Bean
    public AbstractRegNumberGenerationService randomGenerationService(Random random,
                                                                      AtomicInteger triesCount,
                                                                      Set<RegNumber> previousRegNumbers) {

        return new RandomRegNumberGenerationService(random, triesCount, previousRegNumbers);
    }

    @Bean
    public AbstractRegNumberGenerationService sequentialGenerationService() {
        return new SequentialRegNumberGenerationService();
    }
}
