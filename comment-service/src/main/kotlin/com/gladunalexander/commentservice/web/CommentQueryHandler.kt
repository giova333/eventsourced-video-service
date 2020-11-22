package com.gladunalexander.commentservice.web

import com.gladunalexander.commentservice.persistance.CommentRepository
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/comments")
class CommentQueryHandler(private val repository: CommentRepository) {

    @GetMapping("/video/{videoId}")
    fun getCommentsForVideo(@PathVariable videoId: UUID, pageable: Pageable) =
            repository.findByVideoId(pageable, videoId)
                    .map { CommentResponse(it.aggregateId, it.videoId, it.accountId, it.comment, it.createdAt) }

}