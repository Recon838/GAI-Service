package com.raliev.gai.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest()
class RandomRegNumberGenerationServiceTest {

    @Test
    void shouldGenerateNumber() {
        Random random = mock(Random.class);
        when(random.nextInt(eq(12)))
                .thenReturn(1);
        when(random.nextInt(eq(999)))
                .thenReturn(0);

        RandomRegNumberGenerationService service = new RandomRegNumberGenerationService(random, new AtomicInteger(),
                new HashSet<>());

        assertEquals("В000ВВ 116 RUS", service.generate().toString());
    }

    @Test
    void shouldGenerateNumber2() {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt()))
                .thenReturn(0);

        RandomRegNumberGenerationService service = new RandomRegNumberGenerationService(random, new AtomicInteger(),
                new HashSet<>());

        assertEquals("А000АА 116 RUS", service.generate().toString());
    }

    @Test
    void shouldGenerateNumber3() {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt()))
                .thenReturn(1);

        RandomRegNumberGenerationService service = new RandomRegNumberGenerationService(random, new AtomicInteger(),
                new HashSet<>());

        assertEquals("В001ВВ 116 RUS", service.generate().toString());
    }

    @Test
    void shouldThrowWhenNumbersExceed() {
        Random random = mock(Random.class);
        when(random.nextInt(anyInt()))
                .thenReturn(1);

        RandomRegNumberGenerationService service = new RandomRegNumberGenerationService(random,
                new AtomicInteger(1_000_000),
                new HashSet<>());

        assertThrows(RegNumberExceedException.class, service::generate);
    }
}