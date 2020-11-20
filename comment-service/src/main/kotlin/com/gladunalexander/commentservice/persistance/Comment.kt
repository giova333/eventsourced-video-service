package com.gladunalexander.commentservice.persistance

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant
import java.util.UUID

@Document
data class Comment(
        @Id
        val aggregateId: UUID,
        val videoId: UUID,
        val accountId: UUID,
        val comment: String,
        val createdAt: Instant
)