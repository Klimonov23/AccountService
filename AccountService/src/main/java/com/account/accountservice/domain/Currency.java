package com.account.accountservice.domain;

public enum Currency {
    USD,
    RUB,
    EUR;

    public static Currency getDefault(){
        return EUR;
    }
}
