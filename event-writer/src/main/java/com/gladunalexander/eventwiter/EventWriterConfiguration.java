package com.gladunalexander.eventwiter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class EventWriterConfiguration {

    @Bean
    public EventPublisher eventPublisher(KafkaTemplate<String, String> kafkaTemplate,
                                         ObjectMapper objectMapper) {
        return new KafkaEventPublisher(new JustLogEventPublisher(), kafkaTemplate, objectMapper);
    }
}
