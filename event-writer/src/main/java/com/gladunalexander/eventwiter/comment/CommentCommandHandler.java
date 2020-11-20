package com.gladunalexander.eventwiter.comment;

import com.gladunalexander.eventwiter.EventPublisher;
import com.gladunalexander.eventwiter.comment.command.AddCommentCommand;
import com.gladunalexander.eventwiter.comment.event.CommentAddedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
class CommentCommandHandler {

    private static final String COMMENT_TOPIC_NAME = "comments";

    private final EventPublisher eventPublisher;

    @PostMapping("/comment")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handle(@Valid @RequestBody AddCommentCommand command) {
        eventPublisher.publish(CommentAddedEvent.from(command), COMMENT_TOPIC_NAME);
    }

}
