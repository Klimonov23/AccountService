package com.sdi.statisticsservice.domain;
public enum Currency {

    USD, EUR, RUB;

    public static Currency getBase() {
        return EUR;
    }
}