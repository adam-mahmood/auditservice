package com.adam.auditservice.services;

import com.adam.auditservice.api.V1ApiDelegate;

import com.adam.auditservice.avro.schema.AuditRecord;
import com.adam.auditservice.entity.Audit;
import com.adam.auditservice.model.InitialDeposit;
import com.adam.auditservice.model.TotalInterestGainedResponse;
import com.adam.auditservice.repository.AuditRepository;
import com.adam.auditservice.services.kafka.KafkaProducer;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class InterestCalculationDelegate implements V1ApiDelegate {

    final private AuditRepository repository;

    final private TotalInterestGainedCalculator calculator;

    final private KafkaProducer<AuditRecord> kafkaProducer;

    @Override
    public ResponseEntity<TotalInterestGainedResponse> calculateTotalInterest(Long auditId, Long calculationId,
                                                                              InitialDeposit initialDeposit) {
        BigDecimal interestGained = calculator.calculateTotalInterestGained(initialDeposit);

        repository.save(Audit.builder().calculationId(calculationId).interestGained(interestGained)
                .initialDeposit(initialDeposit.getDepositValue()).build());

        //kafkaProducer.send(AuditRecord.newBuilder().);
        return new ResponseEntity<>(new TotalInterestGainedResponse().depositValue(initialDeposit.getDepositValue())
                .totalInterestGained(interestGained)
                , HttpStatus.CREATED);
    }
}
