package com.huo.cloud.util;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class DmsKafkaProduceDemo {
    public static void main(String[] args) throws IOException {
        Properties producerConfig = Config.getProducerConfig();

        producerConfig.put("ssl.truststore.location", Config.getTrustStorePath());
        System.setProperty("java.security.auth.login.config", Config.getSaslConfig());

        Producer<String, String> producer = new KafkaProducer<>(producerConfig);
        for (int i = 0; i < 10; i++) {
            Future<RecordMetadata> future = producer.send(new ProducerRecord<>(producerConfig.getProperty("topic"),
                    null, "hello, dms kafka."));
            RecordMetadata rm;
            try {
                rm = future.get();
                System.out.println("Succeed to send msg: " + rm.offset());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        producer.close();
    }
}
