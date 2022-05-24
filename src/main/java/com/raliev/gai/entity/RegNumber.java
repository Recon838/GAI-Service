package com.raliev.gai.entity;

import java.util.Objects;

public class RegNumber {

    private final String number;
    private final String series;
    private final String country;

    private RegNumber(String number, String series, String country) {
        this.number = number;
        this.series = series;
        this.country = country;
    }

    public static RegNumber of(String number, String series, String country) {
        return new RegNumber(number, series, country);
    }

    @Override
    public String toString() {
        return series.charAt(0) +
                number +
                series.charAt(1) +
                series.charAt(2) +
                " " +
                country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegNumber regNumber = (RegNumber) o;
        return Objects.equals(number, regNumber.number)
                && Objects.equals(series, regNumber.series)
                && Objects.equals(country, regNumber.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, series, country);
    }
}