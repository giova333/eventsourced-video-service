package com.gladunalexander.eventwiter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher {

    private final EventPublisher eventPublisher;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(Event<?> event, String topic) {
        try {
            eventPublisher.publish(event, topic);
            kafkaTemplate.send(
                    topic,
                    event.getAggregateId().toString(),
                    objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Json serialization error", e);
        }
    }
}
