package com.gladunalexander.videostreamprocessor;

import com.gladunalexander.videostreamprocessor.serdes.CountAndSum;
import com.gladunalexander.videostreamprocessor.serdes.Rating;
import com.gladunalexander.videostreamprocessor.serdes.Video;
import com.gladunalexander.videostreamprocessor.serdes.VideoStatistic;
import com.gladunalexander.videostreamprocessor.serdes.View;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonSerde;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaConfiguration {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration kStreamsConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "video-stream-processor");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String, VideoStatistic> kStream(StreamsBuilder builder) {
        KTable<String, Video> videos = builder.table("videos",
                Consumed.with(Serdes.String(), new JsonSerde<>(Video.class)));

        KStream<String, View> views = builder.stream("views",
                Consumed.with(Serdes.String(), new JsonSerde<>(View.class)));

        KTable<String, Long> totalViews = views
                .groupByKey()
                .count();

        KTable<String, VideoStatistic> videoViews = videos.join(totalViews, VideoStatistic::new);

        KStream<String, Rating> ratings = builder.stream("ratings",
                Consumed.with(Serdes.String(), new JsonSerde<>(Rating.class)));

        KTable<String, Double> ratingAvg = ratings
                .selectKey((k1, v1) -> v1.getAccountId().toString())
                .toTable(Materialized.with(Serdes.String(), new JsonSerde<>(Rating.class)))
                .groupBy((k, v) -> new KeyValue<>(v.getAggregateId().toString(), v),
                        Grouped.with(Serdes.String(), new JsonSerde<>(Rating.class)))
                .aggregate(
                        () -> new CountAndSum(0L, 0.0),
                        (key, value, aggregate) -> {
                            aggregate.setCount(aggregate.getCount() + 1);
                            aggregate.setSum(aggregate.getSum() + value.getRating());
                            return aggregate;
                        },
                        (key, value, aggregate) -> {
                            aggregate.setCount(aggregate.getCount() - 1);
                            aggregate.setSum(aggregate.getSum() - value.getRating());
                            return aggregate;
                        }, Materialized.with(Serdes.String(), new JsonSerde<>(CountAndSum.class)

                        )).mapValues(value -> BigDecimal.valueOf(value.getSum() / value.getCount())
                        .setScale(2, RoundingMode.HALF_UP)
                        .doubleValue());

        KStream<String, VideoStatistic> videoStatisticsStream = videoViews.join(ratingAvg, VideoStatistic::setRating).toStream();
        videoStatisticsStream.print(Printed.toSysOut());
        videoStatisticsStream.to("video-statistic", Produced.valueSerde(new JsonSerde<>(VideoStatistic.class)));
        return videoStatisticsStream;
    }
}
