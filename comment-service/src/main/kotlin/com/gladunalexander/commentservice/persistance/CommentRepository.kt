package com.gladunalexander.commentservice.persistance

import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface CommentRepository : MongoRepository<Comment, UUID> {

    fun findByVideoId(videoId: UUID): List<Comment>
}