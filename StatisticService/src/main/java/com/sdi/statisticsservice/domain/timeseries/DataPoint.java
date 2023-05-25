package com.sdi.statisticsservice.domain.timeseries;

import com.sdi.statisticsservice.domain.Currency;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

@Document(collection = "datapoints")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataPoint {

    @Id
    private DataPointId id;

    private Set<ItemMetric> incomes;

    private Set<ItemMetric> expenses;

    private Map<StatisticMetric, BigDecimal> statistics;

    private Map<Currency, BigDecimal> rates;
}
