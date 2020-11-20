package com.gladunalexander.videoservice.events;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class VideoViewedEvent {
    private UUID aggregateId;
    private UUID accountId;
    private Instant occurredAt;
}
