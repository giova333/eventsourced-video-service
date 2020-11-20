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
@Table(name = "video_ratings")
@Getter
@Setter
@NoArgsConstructor
public class VideoRating {

    @Id
    @GeneratedValue
    private Long id;

    private UUID videoId;

    private UUID accountId;

    private Double rating;

    private Instant ratedAt;

    public VideoRating(UUID videoId, UUID accountId, Double rating, Instant ratedAt) {
        this.videoId = videoId;
        this.accountId = accountId;
        this.rating = rating;
        this.ratedAt = ratedAt;
    }

    public VideoRating setRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public VideoRating setRatedAt(Instant ratedAt) {
        this.ratedAt = ratedAt;
        return this;
    }
}
