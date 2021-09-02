package com.adam.auditservice.services.kafka;

import com.adam.auditservice.avro.schema.AuditRecord;

public interface KafkaProducer<T> {
    void send(T t);
}
