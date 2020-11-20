package com.gladunalexander.eventwiter.comment.event;

import com.gladunalexander.eventwiter.Event;
import com.gladunalexander.eventwiter.comment.command.AddCommentCommand;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;
import java.util.UUID;

@Getter
@ToString
public class CommentAddedEvent implements Event<UUID> {

    private final UUID aggregateId;
    private final UUID videoId;
    private final UUID accountId;
    private final String comment;
    private final Instant occurredAt;
    private final String type;


    private CommentAddedEvent(UUID videoId, UUID accountId, String comment) {
        this.aggregateId = UUID.randomUUID();
        this.videoId = videoId;
        this.accountId = accountId;
        this.occurredAt = Instant.now();
        this.comment = comment;
        this.type = "comment-added";
    }

    public static CommentAddedEvent from(AddCommentCommand command) {
        return new CommentAddedEvent(
                command.getVideoId(),
                command.getAccountId(),
                command.getComment()
        );
    }
}
