package com.ecomm.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class BaseKafkaProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public CompletableFuture<SendResult<String, Object>> send(String topic, String key, Object payload) {
        if (key == null) {
            return kafkaTemplate.send(topic, payload);
        }
        return kafkaTemplate.send(topic, key, payload);
    }

    public CompletableFuture<SendResult<String, Object>> send(String topic, Object payload) {
        CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, null, payload);

        future.whenComplete((result, ex) -> {
            if (ex != null) {
                // *** ADD THIS CRITICAL LOGGING ***
                log.atError().log("issue with the {}", ex.getMessage());
            } else {
                log.atError().log("send success.::");
            }
        });
        return future;
    }

}
