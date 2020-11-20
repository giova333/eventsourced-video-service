package com.gladunalexander.accountservice.events;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class AccountRegisteredEvent {
    private UUID aggregateId;
    private String name;
    private String description;
    private Instant occurredAt;
}
