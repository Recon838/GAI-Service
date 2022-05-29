package com.raliev.gai.service.initializer;

import com.raliev.gai.service.RegNumberExceedException;
import com.raliev.gai.service.generator.RegNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class RedisDataInitializer implements DataInitializer {

    private static final int REG_NUMBER_COMBINATION_COUNT = 1_728_000;
    private static final int CHUNK_SIZE = 1000;

    @Autowired
    private RegNumberGenerator regNumberGenerator;

    @Override
    public void init(Consumer<List<String>> consumer) {
        List<String> chunk = new ArrayList<>(CHUNK_SIZE);
        try {
            for (int i = 0; i < REG_NUMBER_COMBINATION_COUNT; i++) {
                chunk.add(regNumberGenerator.generate().toString());
                if (chunk.size() >= CHUNK_SIZE) {
                    consumer.accept(chunk);
                    chunk.clear();
                }
            }
        } catch (RegNumberExceedException ignore) {
        }

        if (chunk.size() > 0) {
            consumer.accept(chunk); // consume remains
        }
    }
}
