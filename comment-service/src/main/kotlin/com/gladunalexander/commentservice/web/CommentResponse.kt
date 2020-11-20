package com.gladunalexander.commentservice.web

import java.time.Instant
import java.util.UUID

data class CommentResponse(
        val aggregateId: UUID,
        val videoId: UUID,
        val accountId: UUID,
        val comment: String,
        val createdAt: Instant)