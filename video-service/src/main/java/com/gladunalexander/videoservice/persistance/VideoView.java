package com.gladunalexander.videoservice.persistance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "video_views")
@Getter
@Setter
@NoArgsConstructor
public class VideoView {

    @Id
    @GeneratedValue
    private Long id;

    private UUID videoId;

    private UUID accountId;

    private Instant viewedAt;

    public VideoView(UUID videoId, UUID accountId, Instant viewedAt) {
        this.videoId = videoId;
        this.accountId = accountId;
        this.viewedAt = viewedAt;
    }
}
