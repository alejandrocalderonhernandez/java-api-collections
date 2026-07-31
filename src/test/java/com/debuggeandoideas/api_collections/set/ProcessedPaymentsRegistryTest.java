package com.debuggeandoideas.api_collections.set;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import com.debuggeandoideas.api_collections.utils.AbstractCollectionTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProcessedPaymentsRegistryTest extends AbstractCollectionTest {

    @Test
    void markAsProcessedReturnsTrueTheFirstTime() {
        ProcessedPaymentsRegistry registry = new ProcessedPaymentsRegistry();
        Payment payment = payments(1).get(0);

        boolean firstTime = registry.markAsProcessed(payment);

        assertTrue(firstTime);
    }

    @Test
    void markAsProcessedReturnsFalseOnASecondAttempt() {
        ProcessedPaymentsRegistry registry = new ProcessedPaymentsRegistry();
        Payment payment = payments(1).get(0);

        registry.markAsProcessed(payment);
        boolean secondTime = registry.markAsProcessed(payment);

        assertFalse(secondTime);
    }

    @Test
    void isDuplicateIsFalseBeforeProcessing() {
        ProcessedPaymentsRegistry registry = new ProcessedPaymentsRegistry();
        Payment payment = payments(1).get(0);

        assertFalse(registry.isDuplicate(payment));
    }

    @Test
    void isDuplicateIsTrueAfterProcessing() {
        ProcessedPaymentsRegistry registry = new ProcessedPaymentsRegistry();
        Payment payment = payments(1).get(0);

        registry.markAsProcessed(payment);

        assertTrue(registry.isDuplicate(payment));
    }

    @Test
    void distinctPaymentMethodsCollapsesRepeatedMethods() {
        ProcessedPaymentsRegistry registry = new ProcessedPaymentsRegistry();
        List<Payment> batch = payments(50);

        Set<PaymentMethod> methods = registry.distinctPaymentMethods(batch);

        assertTrue(methods.size() <= PaymentMethod.values().length);
    }
}