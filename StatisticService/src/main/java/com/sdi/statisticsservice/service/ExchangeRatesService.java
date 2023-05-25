package com.sdi.statisticsservice.service;

import com.sdi.statisticsservice.domain.Currency;

import java.math.BigDecimal;
import java.util.Map;

public interface ExchangeRatesService {
    Map<Currency, BigDecimal> getCurrentRates();


    BigDecimal convert(Currency from, Currency to, BigDecimal amount);
}
