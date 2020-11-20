package com.gladunalexander.videoservice.web;

import lombok.Value;

import java.time.Instant;

@Value
public class VideoViewResponse {
    Instant viewedAt;
    VideoResponse video;
}
