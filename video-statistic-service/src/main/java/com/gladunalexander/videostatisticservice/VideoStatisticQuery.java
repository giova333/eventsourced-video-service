package com.gladunalexander.videostatisticservice;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.kafka.receiver.KafkaReceiver;

@RestController
@RequestMapping("/video-statistic")
public class VideoStatisticQuery {

    private final ConnectableFlux<String> connectableFlux;

    public VideoStatisticQuery(KafkaReceiver<String, String> kafkaReceiver) {
        this.connectableFlux = kafkaReceiver.receive()
                .map(ConsumerRecord::value)
                .publish();

        connectableFlux.connect();
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamStatistic() {
        return connectableFlux;
    }

}
