package com.gladunalexander.videoservice.events;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
public class VideoRegisteredEvent {
    private UUID aggregateId;
    private UUID accountId;
    private String imageUrl;
    private String title;
    private String videoUrl;
    private Instant occurredAt;
}
