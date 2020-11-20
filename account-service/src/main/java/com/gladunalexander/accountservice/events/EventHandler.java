package com.gladunalexander.accountservice.events;

import com.gladunalexander.accountservice.persistance.Account;
import com.gladunalexander.accountservice.persistance.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventHandler {

    private final AccountRepository repository;

    @StreamListener(Channels.ACCOUNTS)
    public void handle(AccountRegisteredEvent event) {
        repository.save(
                new Account(
                        event.getAggregateId(),
                        event.getName(),
                        event.getDescription(),
                        event.getOccurredAt()
                )
        );
    }

    @StreamListener(Channels.SUBSCRIPTIONS)
    public void handle(SubscribedToAccountEvent event) {
        Optional<Account> srcAccount = repository.findById(event.getAggregateId());
        Optional<Account> targetAccount = repository.findById(event.getTargetAccountId());

        if (srcAccount.isEmpty() || targetAccount.isEmpty()) {
            log.warn("Invalid subscription {}. Accounts not found.", event);
            return;
        }

        srcAccount.get().subscribeTo(targetAccount.get());
        repository.save(srcAccount.get());
    }

}
