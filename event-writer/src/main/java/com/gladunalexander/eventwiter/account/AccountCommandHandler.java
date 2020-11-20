package com.gladunalexander.eventwiter.account;

import com.gladunalexander.eventwiter.EventPublisher;
import com.gladunalexander.eventwiter.account.command.SubscribeToAccountCommand;
import com.gladunalexander.eventwiter.account.command.RegisterAccountCommand;
import com.gladunalexander.eventwiter.account.event.AccountRegisteredEvent;
import com.gladunalexander.eventwiter.account.event.SubscribedToAccountEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
class AccountCommandHandler {

    private static final String ACCOUNT_TOPIC_NAME = "accounts";
    private static final String SUBSCRIPTION_TOPIC_NAME = "subscriptions";

    private final EventPublisher eventPublisher;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handle(@Valid @RequestBody RegisterAccountCommand command) {
        eventPublisher.publish(AccountRegisteredEvent.from(command), ACCOUNT_TOPIC_NAME);
    }

    @PostMapping("/subscribe")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handle(@Valid @RequestBody SubscribeToAccountCommand command) {
        eventPublisher.publish(SubscribedToAccountEvent.from(command), SUBSCRIPTION_TOPIC_NAME);
    }

}
