package com.gladunalexander.eventwiter.account.event;

import com.gladunalexander.eventwiter.Event;
import com.gladunalexander.eventwiter.account.command.RegisterAccountCommand;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class AccountRegisteredEvent implements Event<UUID> {

    private final UUID aggregateId;
    private final String name;
    private final String description;
    private final Instant occurredAt;
    private final String type;


    private AccountRegisteredEvent(UUID accountId, String name, String description) {
        this.aggregateId = accountId;
        this.name = name;
        this.description = description;
        this.occurredAt = Instant.now();
        this.type = "account-registered";
    }

    public static AccountRegisteredEvent from(RegisterAccountCommand command) {
        return new AccountRegisteredEvent(command.getAccountId(), command.getName(), command.getDescription());
    }
}
