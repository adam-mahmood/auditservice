package com.adam.auditservice.services;

import com.adam.auditservice.model.InitialDeposit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


import static com.adam.auditservice.utils.InterestMonetaryBoundaries.*;

@Component
@Slf4j
public class TotalInterestGainedCalculator {

    public  BigDecimal calculateTotalInterestGained(InitialDeposit totalInterestRequest) {
        BigDecimal depositValue = totalInterestRequest.getDepositValue();
        BigDecimal response;
        BigDecimal thousandValue = THOUSAND.getValue();
        if (depositValue.compareTo(thousandValue) <= 0){
            response = depositValue.multiply(THOUSAND.getInterest());
        } else if (depositValue.compareTo(FIVE_THOUSAND.getValue()) <= 0){
            response = depositValue.subtract(thousandValue).multiply(FOUR_THOUSAND.getInterest())
                    .add(THOUSAND.calculateInterest());
        } else {
            response = depositValue.subtract(FIVE_THOUSAND.getValue()).multiply(FIVE_THOUSAND.getInterest())
                    .add(FOUR_THOUSAND.calculateInterest())
                    .add(THOUSAND.calculateInterest());
        }
        log.info("deposit:{}, total interest gained:{}", depositValue, response);
        return response;
    }
}
