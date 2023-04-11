package com.statistics.statisticsservice.service;

import com.statistics.statisticsservice.domain.Currency;

import java.math.BigDecimal;
import java.util.Map;

public interface ExchangeRatesService {
    Map<Currency, BigDecimal> getCurrentRates();


    BigDecimal convert(Currency from, Currency to, BigDecimal amount);
}
