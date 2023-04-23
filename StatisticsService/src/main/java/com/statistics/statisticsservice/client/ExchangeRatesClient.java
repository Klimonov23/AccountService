package com.statistics.statisticsservice.client;

import com.statistics.statisticsservice.domain.Currency;
import com.statistics.statisticsservice.domain.ExchangeRateContainer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${rates.url}", name = "rates-client", fallback = ExchangeRatesClientFallback.class)
public interface ExchangeRatesClient { //Feign - это декларативный HTTP клиент, для упрощения написания интеграций с различными АПИ.

    @RequestMapping(method = RequestMethod.GET, value = "/latest")
    ExchangeRateContainer getRates(@RequestParam("base") Currency base);

}
