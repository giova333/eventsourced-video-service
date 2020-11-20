package com.gladunalexander.eventwiter.video.event;

import com.gladunalexander.eventwiter.Event;
import com.gladunalexander.eventwiter.video.command.RateVideoCommand;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class VideoRatedEvent implements Event<UUID> {

    private final UUID aggregateId;
    private final UUID accountId;
    private final double rating;
    private final Instant occurredAt;
    private final String type;


    private VideoRatedEvent(UUID aggregateId, UUID accountId, double rating) {
        this.aggregateId = aggregateId;
        this.accountId = accountId;
        this.occurredAt = Instant.now();
        this.rating = rating;
        this.type = "video-rated";
    }

    public static VideoRatedEvent from(RateVideoCommand command) {
        return new VideoRatedEvent(
                command.getVideoId(),
                command.getAccountId(),
                command.getRating()
        );
    }
}
