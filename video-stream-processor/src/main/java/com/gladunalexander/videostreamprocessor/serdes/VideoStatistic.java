package com.gladunalexander.videostreamprocessor.serdes;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class VideoStatistic {
    private UUID aggregateId;
    private UUID accountId;
    private String title;
    private long views;
    private double rating;

    public VideoStatistic(Video video, long views) {
        this.aggregateId = video.getAggregateId();
        this.accountId = video.getAccountId();
        this.title = video.getTitle();
        this.views = views;
    }

    public VideoStatistic setRating(double rating) {
        this.rating = rating;
        return this;
    }
}
