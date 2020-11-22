package com.gladunalexander.videoservice.web;

import com.gladunalexander.videoservice.persistance.Account;
import com.gladunalexander.videoservice.persistance.AccountRepository;
import com.gladunalexander.videoservice.persistance.Video;
import com.gladunalexander.videoservice.persistance.VideoFullTextSearch;
import com.gladunalexander.videoservice.persistance.VideoRepository;
import com.gladunalexander.videoservice.persistance.VideoView;
import com.gladunalexander.videoservice.persistance.VideoViewRepository;
import com.gladunalexander.videoservice.persistance.Video_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor
public class VideoQueryHandler {

    private final VideoRepository videoRepository;
    private final VideoViewRepository videoViewRepository;
    private final VideoFullTextSearch fullTextSearch;
    private final AccountRepository accountRepository;

    @GetMapping
    public Page<VideoResponse> getVideos(Pageable pageable,
                                         @RequestParam MultiValueMap<String, String> filter) {
        return videoRepository
                .findAll(pageable, filter)
                .map(VideoResponse::new);
    }

    @GetMapping("/search")
    public Page<VideoResponse> search(Pageable pageable,
                                      @RequestParam String text) {
        return fullTextSearch.search(pageable, text)
                .map(VideoResponse::new);
    }

    @GetMapping("/{accountId}/history")
    public Page<VideoViewResponse> getRecentlyViewed(Pageable pageable,
                                                     @PathVariable UUID accountId) {
        Page<VideoView> pageViews = videoViewRepository.findByAccountId(pageable, accountId);
        List<Video> videos = videoRepository.findAllById(
                pageViews.getContent().stream()
                        .map(VideoView::getVideoId)
                        .collect(Collectors.toList())
        );
        return pageViews.map(view -> new VideoViewResponse(
                view.getViewedAt(),
                videos.stream()
                        .filter(video -> video.getId().equals(view.getVideoId()))
                        .map(VideoResponse::new)
                        .findAny()
                        .orElseThrow()
        ));
    }

    @GetMapping("/{accountId}/followed")
    public Page<VideoResponse> getVideos(Pageable pageable,
                                         @PathVariable UUID accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (optionalAccount.isEmpty() || optionalAccount.get().getSubscriptions().isEmpty()) {
            return Page.empty(pageable);
        }
        MultiValueMap<String, String> filter = new LinkedMultiValueMap<>();

        optionalAccount.get().getSubscriptions()
                .forEach(sub -> filter.add(Video_.ACCOUNT_ID, sub.toString()));

        return videoRepository
                .findAll(pageable, filter)
                .map(VideoResponse::new);
    }

}
