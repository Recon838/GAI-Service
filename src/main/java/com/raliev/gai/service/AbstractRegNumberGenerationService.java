package com.raliev.gai.service;

import com.raliev.gai.entity.RegNumber;

public abstract class AbstractRegNumberGenerationService {

    protected final String SORTED_SERIES_SYMBOLS = "АВЕКМНОРСТУХ";

    protected final int SERIES_SYMBOLS_COUNT = SORTED_SERIES_SYMBOLS.length();
    protected final int NUMBER_MAX_VALUE = 999;
    protected final String DEFAULT_COUNTRY = "116 RUS";

    public abstract RegNumber generate();

    protected abstract String generateSeries();

    protected abstract String generateNumber();
}
