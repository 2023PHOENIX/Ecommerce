package com.ecomm.audit.model;

import java.time.LocalDateTime;

public class AuditDocument {

    private String id;
    private String serviceName;
    private String eventType;
    private String entityId;
    private Object payload;
    private LocalDateTime timestamp;
    private String performedBy;
}
