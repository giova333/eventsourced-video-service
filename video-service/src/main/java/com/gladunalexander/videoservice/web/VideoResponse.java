package com.gladunalexander.videoservice.web;

import com.gladunalexander.videoservice.persistance.Video;
import lombok.Getter;

import java.util.UUID;

@Getter
class VideoResponse {

    private final UUID id;
    private final UUID accountId;
    private final String title;
    private final String imageUrl;
    private final String videoUrl;
    private final double rating;
    private final long views;

    public VideoResponse(Video video) {
        this.id = video.getId();
        this.accountId = video.getAccountId();
        this.imageUrl = video.getImageUrl();
        this.title = video.getTitle();
        this.videoUrl = video.getVideoUrl();
        this.rating = video.getRating();
        this.views = video.getViews();
    }
}
