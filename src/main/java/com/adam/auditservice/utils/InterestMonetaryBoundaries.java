package com.adam.auditservice.utils;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum InterestMonetaryBoundaries {
    THOUSAND(1000, 0.01),
    FOUR_THOUSAND(4000, 0.02),
    FIVE_THOUSAND(5000, 0.03);

    InterestMonetaryBoundaries(int monetaryRange, double interest) {
        this.value = BigDecimal.valueOf(monetaryRange);
        this.interest = BigDecimal.valueOf(interest);
    }

    private BigDecimal value;
    private BigDecimal interest;

   public BigDecimal calculateInterest(){
       return  value.multiply(interest);
   }
}
