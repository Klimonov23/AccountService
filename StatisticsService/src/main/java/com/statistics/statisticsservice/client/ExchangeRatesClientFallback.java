package com.statistics.statisticsservice.client;

import com.statistics.statisticsservice.domain.Currency;
import com.statistics.statisticsservice.domain.ExchangeRateContainer;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ExchangeRatesClientFallback implements ExchangeRatesClient {
    @Override
    public ExchangeRateContainer getRates(Currency base) {
        ExchangeRateContainer container = new ExchangeRateContainer();
        container.setBase(Currency.getBase());
        container.setRates(Collections.emptyMap());
        return container;
    }

}
