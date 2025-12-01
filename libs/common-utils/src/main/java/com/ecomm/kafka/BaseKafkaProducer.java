package com.ecomm.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class BaseKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CompletableFuture<SendResult<String, Object>> send(String topic, String key, Object payload) {
        if (key == null) {
            return kafkaTemplate.send(topic, payload);
        }
        return kafkaTemplate.send(topic,key,payload);
    }

    public CompletableFuture<SendResult<String,Object>> send(String topic,Object payload){
        return kafkaTemplate.send(topic,null,payload);
    }

}
