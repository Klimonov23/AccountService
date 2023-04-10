package com.statistics.statisticsservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "accounts")
@JsonIgnoreProperties(ignoreUnknown = true) //ignore unknown properties
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Valid
    @NotNull
     List<Item> incomes;

    @Valid
    @NotNull
    List<Item> expenses;

    @Valid
    @NotNull
     Saving saving;
}
