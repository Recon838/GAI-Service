package com.raliev.gai.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest()
class SequentialRegNumberGenerationServiceTest {

    @Test
    void shouldGenerateFirstNumber() {
        SequentialRegNumberGenerationService service = new SequentialRegNumberGenerationService();

        assertEquals("А000АА 116 RUS", service.generate().toString());
    }

    @Test
    void shouldIterateSequentialNumber() {
        SequentialRegNumberGenerationService service = new SequentialRegNumberGenerationService(399, 8, 1, 0);

        assertEquals("С399ВА 116 RUS", service.generate().toString());
        assertEquals("С400ВА 116 RUS", service.generate().toString());
    }

    @Test
    void shouldIterateSequentialSeries() {
        SequentialRegNumberGenerationService service = new SequentialRegNumberGenerationService(999, 8, 1, 0);

        assertEquals("С999ВА 116 RUS", service.generate().toString());
        assertEquals("С000ВВ 116 RUS", service.generate().toString());
    }

    @Test
    void shouldGenerateLastNumber() {
        SequentialRegNumberGenerationService service = new SequentialRegNumberGenerationService(999, 11, 11, 11);

        assertEquals("Х999ХХ 116 RUS", service.generate().toString());
    }

    @Test
    void shouldThrowWhenNumberExceed() {
        SequentialRegNumberGenerationService service = new SequentialRegNumberGenerationService(999, 11, 11, 11);
        service.generate();

        assertThrows(RegNumberExceedException.class, service::generate);
    }
}