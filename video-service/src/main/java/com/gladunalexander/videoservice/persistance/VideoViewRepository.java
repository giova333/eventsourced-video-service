package com.gladunalexander.videoservice.persistance;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface VideoViewRepository extends JpaRepository<VideoView, Long> {

    @Transactional(readOnly = true)
    Page<VideoView> findByAccountId(Pageable pageable, UUID accountId);

    @Transactional(readOnly = true)
    @Query("select count(v) from VideoView v where v.videoId = ?1")
    long getViewsOf(UUID videoId);
}
