package com.gladunalexander.eventwiter.video.event;

import com.gladunalexander.eventwiter.Event;
import com.gladunalexander.eventwiter.video.command.ViewVideoCommand;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class VideoViewedEvent implements Event<UUID> {

    private final UUID aggregateId;
    private final UUID accountId;
    private final Instant occurredAt;
    private final String type;


    private VideoViewedEvent(UUID aggregateId, UUID accountId) {
        this.aggregateId = aggregateId;
        this.accountId = accountId;
        this.occurredAt = Instant.now();
        this.type = "video-viewed";
    }

    public static VideoViewedEvent from(ViewVideoCommand command) {
        return new VideoViewedEvent(command.getVideoId(), command.getAccountId());
    }
}
