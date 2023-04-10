package com.statistics.statisticsservice.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Saving {
    @NotNull
    BigDecimal amount;

    @NotNull
    Currency currency;

    @NotNull
    BigDecimal interest;

    @NotNull
    Boolean deposit;

    @NotNull
    Boolean capitalization;
}

