package com.gladunalexander.accountservice.events;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Channels {

    String ACCOUNTS = "accounts";
    String SUBSCRIPTIONS = "subscriptions";

    @Input(ACCOUNTS)
    SubscribableChannel accounts();

    @Input(SUBSCRIPTIONS)
    SubscribableChannel subscriptions();
}
