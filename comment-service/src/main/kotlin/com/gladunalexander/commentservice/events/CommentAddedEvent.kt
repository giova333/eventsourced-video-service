package com.gladunalexander.commentservice.events

import java.time.Instant
import java.util.UUID

data class CommentAddedEvent(
        val aggregateId: UUID,
        val videoId: UUID,
        val accountId: UUID,
        val comment: String,
        val occurredAt: Instant
)