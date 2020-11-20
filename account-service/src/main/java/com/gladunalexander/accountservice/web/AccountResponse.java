package com.gladunalexander.accountservice.web;

import com.gladunalexander.accountservice.persistance.Account;
import lombok.Getter;

import java.util.UUID;

@Getter
public class AccountResponse {

    private final UUID id;
    private final String name;
    private final String description;

    private AccountResponse(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static AccountResponse from(Account account) {
        return new AccountResponse(account.getId(), account.getName(), account.getDescription());
    }
}
