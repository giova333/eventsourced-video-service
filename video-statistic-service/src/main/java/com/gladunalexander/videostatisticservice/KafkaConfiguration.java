package com.gladunalexander.videostatisticservice;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverOptions;
import reactor.kafka.receiver.internals.ConsumerFactory;
import reactor.kafka.receiver.internals.DefaultKafkaReceiver;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    private final String bootstrapServers;
    private final String topicName;

    public KafkaConfiguration(@Value("${kafka.bootstrap.servers}") String bootstrapServers,
                              @Value("${kafka.video-statistic.topic}") String topicName) {
        this.bootstrapServers = bootstrapServers;
        this.topicName = topicName;
    }

    @Bean
    KafkaReceiver kafkaReceiver() {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.CLIENT_ID_CONFIG, "video-statistic-client");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "video-statistic-id");
        configProps.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        return new DefaultKafkaReceiver(ConsumerFactory.INSTANCE,
                ReceiverOptions.create(configProps).subscription(Collections.singletonList(topicName))
        );
    }
}
