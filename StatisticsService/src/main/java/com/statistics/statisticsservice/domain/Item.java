package com.statistics.statisticsservice.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;


import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {
    @NotNull
    @Length(min = 1, max = 20)
    String title;

    @NotNull
    BigDecimal amount;

    @NotNull
    Currency currency;

    @NotNull
    TimePeriod period;

    @NotNull
    private String icon;
}

