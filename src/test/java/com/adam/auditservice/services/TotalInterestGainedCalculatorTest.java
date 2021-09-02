package com.adam.auditservice.services;

import com.adam.auditservice.model.InitialDeposit;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class TotalInterestGainedCalculatorTest {
    private TotalInterestGainedCalculator calculator;
    @BeforeEach
    void setUp() {
         calculator = new TotalInterestGainedCalculator();
    }

    @Test
    void calculateTotalInterestGained() {

    }
    @ParameterizedTest(name = "deposit:{0} = totalInterestGained:{1}")
    @CsvSource({
            "6400,132",
            "500,5",
            "3000,50"
    })
    void calculateTotalInterestGained(Long deposit, Double interestGained) {
        BigDecimal result = calculator.calculateTotalInterestGained(new InitialDeposit().depositValue(BigDecimal.valueOf(deposit)));

        assertThat(result).isCloseTo(BigDecimal.valueOf(interestGained), Percentage.withPercentage(0.01));
    }

}