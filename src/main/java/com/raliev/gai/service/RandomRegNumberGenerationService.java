package com.raliev.gai.service;

import com.raliev.gai.entity.RegNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RandomRegNumberGenerationService extends AbstractRegNumberGenerationService {

    @Autowired
    private final Random random;

    @Autowired
    private final AtomicInteger triesCount;

    @Autowired
    private final Set<RegNumber> previousRegNumbers;

    private final int MAX_TRIES_COUNT = 1_000_000;

    public RandomRegNumberGenerationService(Random random, AtomicInteger triesCount, Set<RegNumber> previousRegNumbers) {
        this.random = random;
        this.triesCount = triesCount;
        this.previousRegNumbers = previousRegNumbers;
    }

    @Override
    public RegNumber generate() {
        RegNumber regNumber;

        do {
            regNumber = RegNumber.of(generateNumber(), generateSeries(), DEFAULT_COUNTRY);
            if (triesCount.getAndIncrement() >= MAX_TRIES_COUNT) {
                throw new RegNumberExceedException();
            }
        } while (previousRegNumbers.contains(regNumber));

        return regNumber;
    }

    @Override
    protected String generateSeries() {
        return String.valueOf(SORTED_SERIES_SYMBOLS.charAt(random.nextInt(SERIES_SYMBOLS_COUNT))) +
                SORTED_SERIES_SYMBOLS.charAt(random.nextInt(SERIES_SYMBOLS_COUNT)) +
                SORTED_SERIES_SYMBOLS.charAt(random.nextInt(SERIES_SYMBOLS_COUNT));
    }

    @Override
    protected String generateNumber() {
        return String.format("%03d", random.nextInt(NUMBER_MAX_VALUE));
    }
}
