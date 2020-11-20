package com.gladunalexander.videoservice.persistance;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "videos")
@Indexed(index = "idx_video")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Video {

    @Id
    private UUID id;

    private UUID accountId;

    private String imageUrl;

    @Field
    private String title;

    private String videoUrl;

    private Instant registeredAt;

    private double rating;

    private long views;

    public Video(UUID id, UUID accountId, String imageUrl, String title, String videoUrl, Instant registeredAt) {
        this.id = id;
        this.accountId = accountId;
        this.imageUrl = imageUrl;
        this.title = title;
        this.videoUrl = videoUrl;
        this.registeredAt = registeredAt;
    }
}
