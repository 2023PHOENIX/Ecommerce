package com.ecomm.audit.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {

    private final ElasticsearchClient elasticsearchClient;
    private final ObjectMapper objectMapper;

    @Value("${audit.index.name:product-audit-log}")
    private String indexName;

    public void save(String eventJson) {
        try {
            Map<String, Object> event = objectMapper.readValue(eventJson, Map.class);
            elasticsearchClient.index(i -> i.index(indexName).document(event));
            log.info("audit save success. {}", indexName);
        } catch (Exception e) {
            log.error("audit save fail. {}", e.getCause(), e.fillInStackTrace());
            log.atError().log(e.getMessage());
        }
    }
}