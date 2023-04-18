package com.statistics.statisticsservice.service;


import com.google.common.collect.ImmutableMap;
import com.statistics.statisticsservice.domain.Account;
import com.statistics.statisticsservice.domain.Currency;
import com.statistics.statisticsservice.domain.Item;
import com.statistics.statisticsservice.domain.Saving;
import com.statistics.statisticsservice.domain.timeseries.DataPoint;
import com.statistics.statisticsservice.domain.timeseries.DataPointId;
import com.statistics.statisticsservice.domain.timeseries.ItemMetric;
import com.statistics.statisticsservice.domain.timeseries.StatisticMetric;
import com.statistics.statisticsservice.repository.DataPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService{
    private final Logger log = LoggerFactory.getLogger(getClass());

    private DataPointRepository repository;
    private ExchangeRatesService ratesService;

    @Autowired
    public StatisticsServiceImpl(DataPointRepository repository, ExchangeRatesService ratesService) {
        this.repository = repository;
        this.ratesService = ratesService;
    }





    @Override
    public List<DataPoint> findByAccountName(String name) {
        Assert.hasLength(name,
                () -> "Name for account '" + name + "' must not be empty");
        return repository.findByIdAccount(name);
    }

    @Override
    public DataPoint save(String accountName, Account account) {
        Instant instant= LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        DataPointId dataPointId=new DataPointId(accountName, Date.from(instant));

        Set<ItemMetric> incomes = account.getIncomes().stream()
                .map(this::createItemMetric)
                .collect(Collectors.toSet());

        Set<ItemMetric> expenses = account.getExpenses().stream()
                .map(this::createItemMetric)
                .collect(Collectors.toSet());
        Map<StatisticMetric, BigDecimal> statistics = createStatisticMetrics(incomes, expenses, account.getSaving());

        DataPoint dataPoint = new DataPoint();
        dataPoint.setId(dataPointId);
        dataPoint.setIncomes(incomes);
        dataPoint.setExpenses(expenses);
        dataPoint.setStatistics(statistics);
        dataPoint.setRates(ratesService.getCurrentRates());

        log.debug("new datapoint has been created: {}", dataPoint);

        return repository.save(dataPoint);
    }
    private Map<StatisticMetric, BigDecimal> createStatisticMetrics(Set<ItemMetric> incomes, Set<ItemMetric> expenses, Saving saving) {

        BigDecimal savingAmount = ratesService.convert(saving.getCurrency(), Currency.getBase(), saving.getAmount());

        BigDecimal expensesAmount = expenses.stream()
                .map(ItemMetric::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal incomesAmount = incomes.stream()
                .map(ItemMetric::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return ImmutableMap.of(
                StatisticMetric.EXPENSES_AMOUNT, expensesAmount,
                StatisticMetric.INCOMES_AMOUNT, incomesAmount,
                StatisticMetric.SAVING_AMOUNT, savingAmount
        );
    }

    private ItemMetric createItemMetric(Item item) {

        BigDecimal amount = ratesService
                .convert(item.getCurrency(), Currency.getBase(), item.getAmount())
                .divide(item.getPeriod().getBaseRatio(), 4, RoundingMode.HALF_UP);

        return new ItemMetric(item.getTitle(), amount);
    }
}
