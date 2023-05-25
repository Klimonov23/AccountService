package com.sdi.statisticsservice.service;

import com.sdi.statisticsservice.domain.Account;
import com.sdi.statisticsservice.domain.timeseries.DataPoint;

import java.util.List;

public interface StatisticsService {
    List<DataPoint> findByAccountName(String accountName);
    DataPoint save(String accountName, Account account);
}
