package com.gladunalexander.eventwiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class JustLogEventPublisher implements EventPublisher {

    @Override
    public void publish(Event<?> event, String topic) {
        log.info("Sending event [{}] to the topic [{}]", event, topic);
    }
}
