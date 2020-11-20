package com.gladunalexander.eventwiter.video.event;

import com.gladunalexander.eventwiter.Event;
import com.gladunalexander.eventwiter.video.command.RegisterVideoCommand;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class VideoRegisteredEvent implements Event<UUID> {

    private final UUID aggregateId;
    private final UUID accountId;
    private final String imageUrl;
    private final String title;
    private final String videoUrl;
    private final Instant occurredAt;
    private final String type;


    private VideoRegisteredEvent(String title, UUID accountId, String imageUrl, String videoUrl) {
        this.aggregateId = UUID.randomUUID();
        this.accountId = accountId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.videoUrl = videoUrl;
        this.occurredAt = Instant.now();
        this.type = "video-registered";
    }

    public static VideoRegisteredEvent from(RegisterVideoCommand command) {
        return new VideoRegisteredEvent(
                command.getTitle(),
                command.getAccountId(),
                command.getImageUrl(),
                command.getVideoUrl()
        );
    }
}
