package com.gladunalexander.accountservice.events;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class SubscribedToAccountEvent {

    private final UUID aggregateId;
    private final UUID targetAccountId;
    private final Instant occurredAt;
}
