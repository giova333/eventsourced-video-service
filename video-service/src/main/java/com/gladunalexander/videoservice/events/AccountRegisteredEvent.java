package com.gladunalexander.videoservice.events;

import lombok.Data;

import java.util.UUID;

@Data
public class AccountRegisteredEvent {
    private UUID aggregateId;
}
