package com.statistics.statisticsservice.service;

import com.statistics.statisticsservice.domain.Account;
import com.statistics.statisticsservice.domain.timeseries.DataPoint;

import java.util.List;

public interface StatisticsService {
    List<DataPoint> findByAccountName(String accountName);
    DataPoint save(String accountName, Account account);
}
