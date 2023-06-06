package com.sdi.statisticsservice;

import com.sdi.statisticsservice.client.ExchangeRatesClient;
import com.sdi.statisticsservice.domain.ExchangeRatesContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class StatisticServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(StatisticServiceApplication.class, args);
    }

}
