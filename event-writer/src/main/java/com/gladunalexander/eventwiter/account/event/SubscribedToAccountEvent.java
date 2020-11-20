package com.gladunalexander.eventwiter.account.event;

import com.gladunalexander.eventwiter.Event;
import com.gladunalexander.eventwiter.account.command.SubscribeToAccountCommand;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class SubscribedToAccountEvent implements Event<UUID> {

    private final UUID aggregateId;
    private final UUID targetAccountId;
    private final Instant occurredAt;
    private final String type;

    private SubscribedToAccountEvent(UUID sourceAccountId, UUID targetAccountId) {
        this.aggregateId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.occurredAt = Instant.now();
        this.type = "account-subscribed";
    }

    public static SubscribedToAccountEvent from(SubscribeToAccountCommand command) {
        return new SubscribedToAccountEvent(
                command.getSourceAccountId(),
                command.getTargetAccountId()
        );
    }
}
