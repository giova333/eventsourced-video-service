package com.gladunalexander.accountservice.web;

import com.gladunalexander.accountservice.persistance.Account;
import com.gladunalexander.accountservice.persistance.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountQueryHandler {

    private final AccountRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable UUID id) {
        return repository.findById(id)
                .map(AccountResponse::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/subscriptions")
    public List<AccountResponse> getSubscriptions(@PathVariable UUID id) {
        return repository.findById(id)
                .map(Account::getSubscriptions)
                .map(list -> list.stream().map(AccountResponse::from).collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
}
