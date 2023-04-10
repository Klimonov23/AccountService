package com.account.accountservice.service;


import com.account.accountservice.domain.*;
import com.account.accountservice.repositories.AccountRepository;
import com.account.accountservice.servise.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountServiceTest {

    @InjectMocks
    private AccountServiceImpl accountService; //mockito inserts @Mock into @InjectMock


    @Mock
    private AccountRepository repository;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void shouldFindByName(){
        final Account account= Account.builder()
                .name("klimonov")
                .build();
        when(repository.findByName(account.getName())).thenReturn(account);
        Account found=accountService.findByName(account.getName());
        assertEquals(account,found);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNameIsEmpty() {
        accountService.findByName("");
    }

    @Test
    public void shouldCreateAccountWithGivenUser() {

        User user = new User();
        user.setUsername("test");

        Account account = accountService.createAccount(user);

        assertEquals(user.getUsername(), account.getName());
        assertEquals(0, account.getSaving().getAmount().intValue());
        assertEquals(Currency.getDefault(), account.getSaving().getCurrency());
        assertEquals(0, account.getSaving().getInterest().intValue());
        assertEquals(false, account.getSaving().getDeposit());
        assertEquals(false, account.getSaving().getCapitalization());
        assertNotNull(account.getLastSeen());
    }

    public void shouldSaveChangesWhenUpdatedAccountGiven() {

        Item grocery = new Item();
        grocery.setTitle("Grocery");
        grocery.setAmount(new BigDecimal(10));
        grocery.setCurrency(Currency.USD);
        grocery.setPeriod(TimePeriod.DAY);
        grocery.setIcon("meal");

        Item salary = new Item();
        salary.setTitle("Salary");
        salary.setAmount(new BigDecimal(9100));
        salary.setCurrency(Currency.USD);
        salary.setPeriod(TimePeriod.MONTH);
        salary.setIcon("wallet");

        Saving saving = new Saving();
        saving.setAmount(new BigDecimal(1500));
        saving.setCurrency(Currency.USD);
        saving.setInterest(new BigDecimal("3.32"));
        saving.setDeposit(true);
        saving.setCapitalization(false);

        final Account update = new Account();
        update.setName("test");
        update.setNote("test note");
        update.setIncomes(List.of(salary));
        update.setExpenses(List.of(grocery));
        update.setSaving(saving);

        final Account account = new Account();

        when(accountService.findByName("test")).thenReturn(account);
        accountService.saveChanges("test", update);

        assertEquals(update.getNote(), account.getNote());
        assertNotNull(account.getLastSeen());

        assertEquals(update.getSaving().getAmount(), account.getSaving().getAmount());
        assertEquals(update.getSaving().getCurrency(), account.getSaving().getCurrency());
        assertEquals(update.getSaving().getInterest(), account.getSaving().getInterest());
        assertEquals(update.getSaving().getDeposit(), account.getSaving().getDeposit());
        assertEquals(update.getSaving().getCapitalization(), account.getSaving().getCapitalization());

        assertEquals(update.getExpenses().size(), account.getExpenses().size());
        assertEquals(update.getIncomes().size(), account.getIncomes().size());

        assertEquals(update.getExpenses().get(0).getTitle(), account.getExpenses().get(0).getTitle());
        assertEquals(0, update.getExpenses().get(0).getAmount().compareTo(account.getExpenses().get(0).getAmount()));
        assertEquals(update.getExpenses().get(0).getCurrency(), account.getExpenses().get(0).getCurrency());
        assertEquals(update.getExpenses().get(0).getPeriod(), account.getExpenses().get(0).getPeriod());
        assertEquals(update.getExpenses().get(0).getIcon(), account.getExpenses().get(0).getIcon());

        assertEquals(update.getIncomes().get(0).getTitle(), account.getIncomes().get(0).getTitle());
        assertEquals(0, update.getIncomes().get(0).getAmount().compareTo(account.getIncomes().get(0).getAmount()));
        assertEquals(update.getIncomes().get(0).getCurrency(), account.getIncomes().get(0).getCurrency());
        assertEquals(update.getIncomes().get(0).getPeriod(), account.getIncomes().get(0).getPeriod());
        assertEquals(update.getIncomes().get(0).getIcon(), account.getIncomes().get(0).getIcon());
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenNoAccountsExistedWithGivenName() {
        final Account update = new Account();
        update.setIncomes(List.of(new Item()));
        update.setExpenses(List.of(new Item()));

        when(accountService.findByName("test")).thenReturn(null);
        accountService.saveChanges("test", update);
    }
}
