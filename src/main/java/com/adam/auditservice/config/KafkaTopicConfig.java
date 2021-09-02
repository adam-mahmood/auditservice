package com.adam.auditservice.config;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "topic")
@Getter
@Setter
public class KafkaTopicConfig {

    String  name;

    Integer partitionsNum;

    short replicationFactor;


    //Spring Boot creates a new Kafka topic based on the provided configurations.
    @Bean
    NewTopic auditTopic() {
        return new NewTopic(name, partitionsNum, replicationFactor);
    }
}
