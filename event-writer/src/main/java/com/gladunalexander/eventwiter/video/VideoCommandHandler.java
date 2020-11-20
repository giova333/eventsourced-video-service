package com.gladunalexander.eventwiter.video;

import com.gladunalexander.eventwiter.EventPublisher;
import com.gladunalexander.eventwiter.video.command.RateVideoCommand;
import com.gladunalexander.eventwiter.video.command.RegisterVideoCommand;
import com.gladunalexander.eventwiter.video.command.ViewVideoCommand;
import com.gladunalexander.eventwiter.video.event.VideoRatedEvent;
import com.gladunalexander.eventwiter.video.event.VideoRegisteredEvent;
import com.gladunalexander.eventwiter.video.event.VideoViewedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
class VideoCommandHandler {

    private static final String VIDEO_TOPIC_NAME = "videos";
    private static final String RATING_TOPIC_NAME = "ratings";
    private static final String VIEW_TOPIC_NAME = "views";

    private final EventPublisher eventPublisher;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handle(@Valid @RequestBody RegisterVideoCommand command) {
        eventPublisher.publish(VideoRegisteredEvent.from(command), VIDEO_TOPIC_NAME);
    }

    @PostMapping("/rate")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handle(@Valid @RequestBody RateVideoCommand command) {
        eventPublisher.publish(VideoRatedEvent.from(command), RATING_TOPIC_NAME);
    }

    @PostMapping("/view")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void handle(@Valid @RequestBody ViewVideoCommand command) {
        eventPublisher.publish(VideoViewedEvent.from(command), VIEW_TOPIC_NAME);
    }

}
