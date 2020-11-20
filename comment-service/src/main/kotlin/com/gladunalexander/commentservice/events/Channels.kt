package com.gladunalexander.commentservice.events

import org.springframework.cloud.stream.annotation.Input
import org.springframework.messaging.SubscribableChannel

interface Channels {

    @Input("comments")
    fun comments(): SubscribableChannel
}