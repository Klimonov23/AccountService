package com.account.accountservice.domain;


import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotNull
    @Length(min = 3, max = 20)
    String username;

    @NotNull
    @Length(min=6,max=40)
    String password;


}
