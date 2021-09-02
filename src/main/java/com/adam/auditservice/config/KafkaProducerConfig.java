package com.adam.auditservice.config;

import com.adam.auditservice.avro.schema.AuditRecord;
import com.adam.auditservice.services.kafka.AuditKafkaProducer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);

        return props;
    }

    @Bean
    public ProducerFactory<String, AuditRecord> producerFactory(Map<String, Object> producerConfigs) {
        return new DefaultKafkaProducerFactory<>(producerConfigs);
    }

    @Bean
    public KafkaTemplate<String, AuditRecord> kafkaTemplate(ProducerFactory<String, AuditRecord> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

    @Bean
    public AuditKafkaProducer auditProducer(KafkaTemplate<String, AuditRecord> kafkaTemplate,
                                            KafkaTopicConfig kafkaTopicConfig) {
        return new AuditKafkaProducer(kafkaTemplate, kafkaTopicConfig);
    }
}
