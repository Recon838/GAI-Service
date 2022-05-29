package com.raliev.gai.service.generator;

import com.raliev.gai.entity.RegNumber;
import com.raliev.gai.service.RegNumberExceedException;
import org.springframework.stereotype.Service;

@Service
public class SequentialRegNumberGenerator implements RegNumberGenerator {

    private final String SORTED_SERIES_SYMBOLS = "АВЕКМНОРСТУХ";
    private final int SERIES_SYMBOLS_COUNT = SORTED_SERIES_SYMBOLS.length();
    private final int NUMBER_MAX_VALUE = 999;
    private final String DEFAULT_COUNTRY = "116 RUS";

    private int number = 0;

    private int seriesDigit1 = 0;
    private int seriesDigit2 = 0;
    private int seriesDigit3 = 0;

    private boolean limitExceeded = false;

    @Override
    public RegNumber generate() {
        if (limitExceeded) {
            throw new RegNumberExceedException();
        }
        RegNumber regNumber = RegNumber.of(generateNumber(), generateSeries(), DEFAULT_COUNTRY);
        increment();
        return regNumber;
    }

    private String generateSeries() {
        return String.valueOf(SORTED_SERIES_SYMBOLS.charAt(seriesDigit1)) +
                SORTED_SERIES_SYMBOLS.charAt(seriesDigit2) +
                SORTED_SERIES_SYMBOLS.charAt(seriesDigit3);
    }

    private String generateNumber() {
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
            if (seriesDigit3 == (SERIES_SYMBOLS_COUNT)) {
                seriesDigit3 = 0;
                seriesDigit2++;
            }

            if (seriesDigit2 == (SERIES_SYMBOLS_COUNT)) {
                seriesDigit2 = 0;
                seriesDigit1++;
            }

            if (seriesDigit1 >= (SERIES_SYMBOLS_COUNT)) {
                limitExceeded = true;
            }
        }
    }
}
