package com.statistics.statisticsservice.service;

import com.google.common.collect.ImmutableMap;
import com.statistics.statisticsservice.client.ExchangeRatesClient;
import com.statistics.statisticsservice.domain.Currency;
import com.statistics.statisticsservice.domain.ExchangeRateContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

import static org.springframework.util.Assert.notNull;

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private static final Logger log = LoggerFactory.getLogger(ExchangeRatesServiceImpl.class);
    private ExchangeRateContainer container;
    private ExchangeRatesClient client;

    @Autowired
    public ExchangeRatesServiceImpl(ExchangeRatesClient client) {
        this.client = client;
    }

    @Override
    public Map<Currency, BigDecimal> getCurrentRates() {
        if (container == null || !container.getDate().equals(LocalDate.now())) {
            container = client.getRates(Currency.getBase());
            log.info("exchange rates has been updated: {}", container);
        }
        return ImmutableMap.of(
                Currency.EUR, container.getRates().get(Currency.EUR.name()),
                Currency.RUB, container.getRates().get(Currency.RUB.name()),
                Currency.USD, BigDecimal.ONE
        );
    }

    @Override
    public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {
        notNull(amount,()-> "Amount  hasn't exist now");

        Map<Currency, BigDecimal> rates = getCurrentRates();
        BigDecimal ratio = rates.get(to).divide(rates.get(from), 4, RoundingMode.HALF_UP);

        return amount.multiply(ratio);
    }
}
