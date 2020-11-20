package com.gladunalexander.videoservice.events;

import com.gladunalexander.videoservice.persistance.Video;
import com.gladunalexander.videoservice.persistance.VideoRating;
import com.gladunalexander.videoservice.persistance.VideoRatingRepository;
import com.gladunalexander.videoservice.persistance.VideoRepository;
import com.gladunalexander.videoservice.persistance.VideoView;
import com.gladunalexander.videoservice.persistance.VideoViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
@Component
@Transactional
@RequiredArgsConstructor
public class EventHandler {

    private static final String VIDEO_DOESNT_EXIST = "Video with id [{}] doesn't exist";

    private final VideoRepository videoRepository;
    private final VideoRatingRepository videoRatingRepository;
    private final VideoViewRepository videoViewRepository;

    @StreamListener(Channels.VIDEOS)
    public void handle(VideoRegisteredEvent event) {
        videoRepository.save(
                new Video(
                        event.getAggregateId(),
                        event.getAccountId(),
                        event.getImageUrl(),
                        event.getTitle(),
                        event.getVideoUrl(),
                        event.getOccurredAt()
                )
        );
    }

    @StreamListener(Channels.RATINGS)
    public void handle(VideoRatedEvent event) {
        Optional<Video> optionalVideo = videoRepository.findById(event.getAggregateId());

        if (optionalVideo.isEmpty()) {
            log.warn(VIDEO_DOESNT_EXIST, event.getAggregateId());
            return;
        }
        Optional<VideoRating> ratedByUserVideo = videoRatingRepository
                .findByAccountIdAndVideoId(event.getAccountId(), event.getAggregateId());

        videoRatingRepository.save(
                ratedByUserVideo
                        .map(video -> video.setRating(event.getRating()).setRatedAt(event.getOccurredAt()))
                        .orElseGet(() -> new VideoRating(
                                event.getAggregateId(),
                                event.getAccountId(),
                                event.getRating(),
                                event.getOccurredAt()
                        ))
        );

        Video video = optionalVideo.get();
        double rating = BigDecimal.valueOf(videoRatingRepository.getRatingOf(video.getId()))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        video.setRating(rating);
    }

    @StreamListener(Channels.VIEWS)
    public void handle(VideoViewedEvent event) {
        Optional<Video> optionalVideo = videoRepository.findById(event.getAggregateId());

        if (optionalVideo.isEmpty()) {
            log.warn(VIDEO_DOESNT_EXIST, event.getAggregateId());
            return;
        }
        videoViewRepository.save(
                new VideoView(
                        event.getAggregateId(),
                        event.getAccountId(),
                        event.getOccurredAt()
                )
        );
        Video video = optionalVideo.get();
        video.setViews(videoViewRepository.getViewsOf(video.getId()));
    }
}
