package com.gladunalexander.commentservice.events

import com.gladunalexander.commentservice.persistance.Comment
import com.gladunalexander.commentservice.persistance.CommentRepository
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.stereotype.Component

@Component
class EventHandler(private val repository: CommentRepository) {

    @StreamListener("comments")
    fun handle(event: CommentAddedEvent) {
        repository.save(
                Comment(
                        event.aggregateId,
                        event.videoId,
                        event.accountId,
                        event.comment,
                        event.occurredAt)
        )
    }

}