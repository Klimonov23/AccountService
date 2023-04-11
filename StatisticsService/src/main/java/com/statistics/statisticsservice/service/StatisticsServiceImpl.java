package com.statistics.statisticsservice.service;


import com.statistics.statisticsservice.domain.Account;
import com.statistics.statisticsservice.domain.timeseries.DataPoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService{
    @Override
    public List<DataPoint> findByAccountName(String accountName) {
        return null;
    }

    @Override
    public DataPoint save(String accountName, Account account) {
        return null;
    }
}
