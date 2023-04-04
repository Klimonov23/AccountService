package com.account.accountservice.controller;

import com.account.accountservice.domain.Account;
import com.account.accountservice.domain.User;
import com.account.accountservice.servise.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AccountController {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping(path = "/{name}")
    public Account getAccountByName(@PathVariable String name){
        return service.findByName(name);
    }

    @GetMapping(path = "/current")
    public Account getCurrentAccount(Principal principal){
        return service.findByName(principal.getName());
    }


    @PutMapping(path = "/current")
    public void saveCurrentAccount(Principal principal, @Valid @RequestBody Account account) {
        service.saveChanges(principal.getName(), account);
    }

    @PostMapping(path = "/")
    public Account createNewAccount(@Valid @RequestBody User user) {
        return service.createAccount(user);
    }
}
