package com.statistics.statisticsservice.domain;
public enum Currency {

    USD, EUR, RUB;

    public static Currency getBase() {
        return RUB;
    }
}