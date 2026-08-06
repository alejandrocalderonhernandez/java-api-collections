package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentAggregatorTest {

    private final PaymentAggregator aggregator = new PaymentAggregator();

    @Test
    void totalAmountByMethodSumsAmountsPerMethod() {
        List<Payment> data = List.of(
                new Payment("PAY-001", 100.0, PaymentMethod.CARD, Instant.now()),
                new Payment("PAY-002", 50.0, PaymentMethod.CARD, Instant.now()),
                new Payment("PAY-003", 30.0, PaymentMethod.CASH, Instant.now())
        );

        Map<PaymentMethod, Double> totals = aggregator.totalAmountByMethod(data);

        assertEquals(150.0, totals.get(PaymentMethod.CARD));
        assertEquals(30.0, totals.get(PaymentMethod.CASH));
    }

    @Test
    void countByMethodCountsPaymentsPerMethod() {
        List<Payment> data = List.of(
                new Payment("PAY-001", 100.0, PaymentMethod.TRANSFER, Instant.now()),
                new Payment("PAY-002", 50.0, PaymentMethod.TRANSFER, Instant.now()),
                new Payment("PAY-003", 30.0, PaymentMethod.WALLET, Instant.now())
        );

        Map<PaymentMethod, Integer> counts = aggregator.countByMethod(data);

        assertEquals(2, counts.get(PaymentMethod.TRANSFER));
        assertEquals(1, counts.get(PaymentMethod.WALLET));
    }

    @Test
    void totalAmountByMethodReturnsEmptyMapForEmptyList() {
        Map<PaymentMethod, Double> totals = aggregator.totalAmountByMethod(List.of());

        assertTrue(totals.isEmpty());
    }
}