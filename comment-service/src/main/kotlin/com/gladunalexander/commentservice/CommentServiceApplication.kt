package com.gladunalexander.commentservice

import com.gladunalexander.commentservice.events.Channels
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.stream.annotation.EnableBinding

@SpringBootApplication
@EnableBinding(Channels::class)
class CommentServiceApplication

fun main(args: Array<String>) {
    runApplication<CommentServiceApplication>(*args)
}
