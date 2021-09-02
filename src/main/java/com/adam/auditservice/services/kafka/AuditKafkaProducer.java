package com.adam.auditservice.services.kafka;

import com.adam.auditservice.avro.schema.AuditRecord;
import com.adam.auditservice.config.KafkaTopicConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile("!mock")
public class AuditKafkaProducer implements KafkaProducer<AuditRecord> {

    final private KafkaTemplate<String, AuditRecord> kafkaTemplate;

    final private KafkaTopicConfig kafkaTopicConfig;

    @Autowired
    public AuditKafkaProducer(KafkaTemplate<String, AuditRecord> kafkaTemplate, KafkaTopicConfig kafkaConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopicConfig = kafkaConfig;
    }

    @Override
    public void send(AuditRecord auditRecord) {
        log.info("sending auditRecord='{}'", auditRecord.toString());
        kafkaTemplate.send(kafkaTopicConfig.getName(),auditRecord.getCalculationId().toString(), auditRecord);
    }
}
