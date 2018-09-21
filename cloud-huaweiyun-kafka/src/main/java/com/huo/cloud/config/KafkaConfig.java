package com.huo.cloud.config;

import com.huo.cloud.util.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;

import java.util.Map;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.setting.dialect.Props;

//@Configuration
//@EnableKafka
public class KafkaConfig {
    private static Props CONSUMER_PROPS = new Props("consumer.properties");
    private static Props PRODUCER_PROPS = new Props("producer.properties");

    @Bean
    public KafkaTemplate<?, ?> kafkaTemplate(
            ProducerFactory<Object, Object> kafkaProducerFactory,
            ProducerListener<Object, Object> kafkaProducerListener) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<Object, Object>(
                kafkaProducerFactory);
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        kafkaTemplate.setDefaultTopic(CONSUMER_PROPS.getStr("topic"));
        return kafkaTemplate;
    }

    @Bean
    public ProducerListener<Object, Object> kafkaProducerListener() {
        return new LoggingProducerListener<>();
    }

    @Bean
    public ConsumerFactory<?, ?> kafkaConsumerFactory() {
        CONSUMER_PROPS.put("ssl.truststore.location", new ClassPathResource("client.truststore.jks").getAbsolutePath());
        return new DefaultKafkaConsumerFactory<Object, Object>((Map) CONSUMER_PROPS);
    }

    @Bean
    public ProducerFactory<?, ?> kafkaProducerFactory() {
        PRODUCER_PROPS.put("ssl.truststore.location", new ClassPathResource("client.truststore.jks").getAbsolutePath());
        return new DefaultKafkaProducerFactory<Object, Object>((Map) PRODUCER_PROPS);
    }


}