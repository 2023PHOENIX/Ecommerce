package com.ecomm.audit.consumers;

import com.ecomm.audit.service.AuditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductEventConsumer {
    private final AuditService auditService;


    @KafkaListener(topics="product.created",groupId = "audit-service")
    public void consume(@Payload String event){
        log.info("Received product event : {}",event);
        auditService.save(event);
    }
}
