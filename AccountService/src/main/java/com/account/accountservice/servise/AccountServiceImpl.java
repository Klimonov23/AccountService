package com.account.accountservice.servise;

import com.account.accountservice.domain.Account;
import com.account.accountservice.domain.Currency;
import com.account.accountservice.domain.Saving;
import com.account.accountservice.domain.User;
import com.account.accountservice.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class AccountServiceImpl implements AccountService{

    private final Logger log = LoggerFactory.getLogger(getClass());


    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account findByName(String name) {
        Assert.hasLength(name,
                () -> "Name for account '" + name + "' must not be empty");
        return repository.findByName(name);
    }

    @Override
    public Account createAccount(User user) {
        log.info("createAccount");
        Account existing=repository.findByName(user.getUsername());
        Assert.isNull(existing,()-> String.format("User %s has already exist",user.getUsername()));

        Saving saving =Saving.builder()
                .amount(new BigDecimal(0))
                .capitalization(false)
                .deposit(false)
                .interest(new BigDecimal(0))
                .currency(Currency.getDefault())
                .build();

        Account account=Account.builder()
                .name(user.getUsername())
                .lastSeen(new Date())
                .saving(saving)
                .build();

        repository.save(account);
        log.info("new account has been created: " + account.getName());
        return account;
    }

    @Override
    public void saveChanges(String name, Account update) {
        Account account=repository.findByName(name);
        Assert.notNull(account,()-> String.format("User %s hasn't exist now",name));

        account.setIncomes(update.getIncomes());
        account.setExpenses(update.getExpenses());
        account.setSaving(update.getSaving());
        account.setNote(update.getNote());
        account.setLastSeen(new Date());
        repository.save(account);

        log.debug("account {} changes has been saved", name);

    }
}
