package com.gladunalexander.videoservice.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface VideoRatingRepository extends JpaRepository<VideoRating, Long> {

    @Query("select avg(r.rating) from VideoRating r where r.videoId = ?1")
    double getRatingOf(UUID videoId);

    Optional<VideoRating> findByAccountIdAndVideoId(UUID accountId, UUID videoId);
}
