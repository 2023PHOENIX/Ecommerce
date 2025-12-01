package com.ecomm.pct.kafka;

import com.ecomm.events.ProducerCreateEvent;
import com.ecomm.kafka.BaseKafkaProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductEventPublisher {

    private final BaseKafkaProducer producer;
    private static final String TOPIC = "product.created";

    public void publishProductCreated(ProducerCreateEvent productCreatedEvent){
        producer.send(TOPIC,productCreatedEvent);
    }
}
