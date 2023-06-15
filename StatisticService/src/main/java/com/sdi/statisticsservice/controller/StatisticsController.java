package com.sdi.statisticsservice.controller;

import com.sdi.statisticsservice.client.ExchangeRatesClient;
import com.sdi.statisticsservice.domain.Account;
import com.sdi.statisticsservice.domain.ExchangeRatesContainer;
import com.sdi.statisticsservice.service.StatisticsService;
import com.sdi.statisticsservice.domain.timeseries.DataPoint;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;
    private final ExchangeRatesClient client;
    @Autowired
    public StatisticsController(StatisticsService service, ExchangeRatesClient client){
        statisticsService=service;
        this.client = client;
    }
    @GetMapping(path = "/current")
    public List<DataPoint> getCurrentAccountStatistics(Principal principal) {
        return statisticsService.findByAccountName(principal.getName());
    }

    //@PreAuthorize("#oauth2.hasScope('server') or #accountName.equals('demo')")
    @GetMapping(path = "/{accountName}")
    public List<DataPoint> getStatisticsByAccountName(@PathVariable String accountName) {
        return statisticsService.findByAccountName(accountName);
    }

    //@PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(value = "/{accountName}", method = RequestMethod.PUT)
    public void saveAccountStatistics(@PathVariable String accountName, @Valid @RequestBody Account account) {
        statisticsService.save(accountName, account);
    }

    @GetMapping(path = "/rates")
    public ResponseEntity<String> getCurrentRates(){
        System.out.println(client.getRates().toString());
        return  ResponseEntity.ok("Succes");
    }
}
