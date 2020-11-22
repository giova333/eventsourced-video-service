package com.gladunalexander.videoservice.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    String VIDEOS = "videos";
    String RATINGS = "ratings";
    String VIEWS = "views";
    String ACCOUNTS = "accounts";
    String SUBSCRIPTIONS = "subscriptions";

    @Input(VIDEOS)
    SubscribableChannel videos();

    @Input(RATINGS)
    SubscribableChannel ratings();

    @Input(VIEWS)
    SubscribableChannel views();

    @Input(ACCOUNTS)
    SubscribableChannel accounts();

    @Input(SUBSCRIPTIONS)
    SubscribableChannel subscriptions();
}
