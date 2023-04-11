package com.statistics.statisticsservice.service;

import com.statistics.statisticsservice.domain.Currency;

import java.math.BigDecimal;
import java.util.Map;

public class ExchangeRatesServiceImpl implements ExchangeRatesService{
    @Override
    public Map<Currency, BigDecimal> getCurrentRates() {
        return null;
    }

    @Override
    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        return null;
    }
}
