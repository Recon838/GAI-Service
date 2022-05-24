package com.raliev.gai.service;

import com.raliev.gai.entity.RegNumber;
import org.springframework.stereotype.Service;

@Service
public class SequentialRegNumberGenerationService extends AbstractRegNumberGenerationService {

    private int number = 0;
    private int seriesDigit1 = 0;
    private int seriesDigit2 = 0;
    private int seriesDigit3 = 0;

    private boolean limitExceeded = false;

    public SequentialRegNumberGenerationService() {}

    public SequentialRegNumberGenerationService(int number, int seriesDigit1, int seriesDigit2, int seriesDigit3) {
        this.number = number;
        this.seriesDigit1 = seriesDigit1;
        this.seriesDigit2 = seriesDigit2;
        this.seriesDigit3 = seriesDigit3;
    }

    @Override
    public RegNumber generate() {
        if (limitExceeded ) {
            throw new RegNumberExceedException();
        }
        RegNumber regNumber = RegNumber.of(generateNumber(), generateSeries(), DEFAULT_COUNTRY);
        increment();
        return regNumber;
    }

    @Override
    protected String generateSeries() {
        return String.valueOf(SORTED_SERIES_SYMBOLS.charAt(seriesDigit1)) +
                SORTED_SERIES_SYMBOLS.charAt(seriesDigit2) +
                SORTED_SERIES_SYMBOLS.charAt(seriesDigit3);
    }

    @Override
    protected String generateNumber() {
        return String.format("%03d", number);
    }

    private synchronized void increment() {
        if (number < NUMBER_MAX_VALUE) {
            // just increment number
            number++;
        } else {
            // reset number and increment series
            number = 0;
            seriesDigit3++;
            if (seriesDigit3 == (SERIES_SYMBOLS_COUNT - 1)) {
                seriesDigit3 = 0;
                seriesDigit2++;
            }

            if (seriesDigit2 == (SERIES_SYMBOLS_COUNT - 1)) {
                seriesDigit2 = 0;
                seriesDigit1++;
            }

            if (seriesDigit1 >= (SERIES_SYMBOLS_COUNT - 1)) {
                limitExceeded = true;
            }
        }
    }
}
