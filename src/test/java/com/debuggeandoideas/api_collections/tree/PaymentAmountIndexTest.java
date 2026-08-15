package com.debuggeandoideas.api_collections.tree;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentAmountIndexTest {

    private Payment payment(double amount) {
        return new Payment("PAY-" + amount, amount, PaymentMethod.CARD, Instant.now());
    }

    private PaymentAmountIndex buildIndex() {
        PaymentAmountIndex index = new PaymentAmountIndex();
        index.add(payment(50.0));
        index.add(payment(100.0));
        index.add(payment(150.0));
        index.add(payment(200.0));
        return index;
    }

    @Test
    void underReturnsOnlyPaymentsBelowTheGivenAmount() {
        Map<Double, Payment> result = buildIndex().under(150.0);

        assertEquals(2, result.size());
        assertTrue(result.containsKey(50.0));
        assertFalse(result.containsKey(150.0));
    }

    @Test
    void atLeastReturnsPaymentsFromTheGivenAmountOnward() {
        Map<Double, Payment> result = buildIndex().atLeast(100.0);

        assertEquals(3, result.size());
        assertTrue(result.containsKey(100.0));
    }

    @Test
    void betweenReturnsOnlyPaymentsInsideTheRange() {
        Map<Double, Payment> result = buildIndex().between(50.0, 200.0);

        assertEquals(3, result.size());
        assertTrue(result.containsKey(50.0));
        assertFalse(result.containsKey(200.0));
    }
}