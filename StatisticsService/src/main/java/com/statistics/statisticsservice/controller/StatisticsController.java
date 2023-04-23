package com.statistics.statisticsservice.controller;

import com.statistics.statisticsservice.domain.Account;
import com.statistics.statisticsservice.domain.timeseries.DataPoint;
import com.statistics.statisticsservice.service.StatisticsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService service){
        statisticsService=service;
    }
    @GetMapping(path = "/current")
    public List<DataPoint> getCurrentAccountStatistics(Principal principal) {
        return statisticsService.findByAccountName(principal.getName());
    }

    @PreAuthorize("#oauth2.hasScope('server') or #accountName.equals('demo')")
    @GetMapping(path = "/{accountName}")
    public List<DataPoint> getStatisticsByAccountName(@PathVariable String accountName) {
        return statisticsService.findByAccountName(accountName);
    }

    @PreAuthorize("#oauth2.hasScope('server')")
    @RequestMapping(value = "/{accountName}", method = RequestMethod.PUT)
    public void saveAccountStatistics(@PathVariable String accountName, @Valid @RequestBody Account account) {
        statisticsService.save(accountName, account);
    }
}
