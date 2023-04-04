package com.account.accountservice.servise;

import com.account.accountservice.domain.Account;
import com.account.accountservice.domain.User;

public interface AccountService {
    Account findByName(String name);

    Account createAccount(User user);

    void saveChanges(String name, Account update);

}
