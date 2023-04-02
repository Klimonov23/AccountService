package com.account.accountservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
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

    @Id
    String name;
    Date lastSeen;

    @Valid
    List<Item> expenses;

    @Valid
    List<Item> incomes;

    @Length(min = 0,max=20000)
    String note;
    @Valid
    @NotNull
    Saving saving;
}
