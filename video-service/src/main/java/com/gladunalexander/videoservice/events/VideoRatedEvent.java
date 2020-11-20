package com.gladunalexander.videoservice.events;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class VideoRatedEvent {
    private UUID aggregateId;
    private UUID accountId;
    private double rating;
    private Instant occurredAt;
}
