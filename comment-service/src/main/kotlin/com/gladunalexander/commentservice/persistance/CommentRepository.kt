package com.gladunalexander.commentservice.persistance

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.UUID

interface CommentRepository : MongoRepository<Comment, UUID> {

    fun findByVideoId(pageable: Pageable, videoId: UUID): Page<Comment>
}