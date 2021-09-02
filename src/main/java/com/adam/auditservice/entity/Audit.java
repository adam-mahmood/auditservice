package com.adam.auditservice.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "audits")
@Builder
@Data
public class Audit {
    @Id
    private String id;
    @Indexed
    private Long calculationId;
    private BigDecimal initialDeposit;
    private BigDecimal interestGained;
}
