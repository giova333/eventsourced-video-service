package com.gladunalexander.videoservice.events;

import lombok.Data;

import java.util.UUID;

@Data
public class SubscribedToAccountEvent {

    private UUID aggregateId;
    private UUID targetAccountId;
}
