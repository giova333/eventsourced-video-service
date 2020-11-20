package com.gladunalexander.eventwiter;

public interface EventPublisher {

    void publish(Event<?> event, String topic);
}
