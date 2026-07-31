package com.debuggeandoideas.api_collections.set;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class ProcessedPaymentsRegistry {

    private final Set<String> processedReferences;

    public ProcessedPaymentsRegistry() {
        this.processedReferences = new HashSet<>();
    }

    public boolean markAsProcessed(Payment payment) {
        return this.processedReferences.add(payment.reference());
    }

    public boolean isDuplicate(Payment payment) {
        return this.processedReferences.contains(payment.reference());
    }

    public Set<PaymentMethod> distinctPaymentMethods(List<Payment> batch) {
        Set<PaymentMethod> paymentMethods = new HashSet<>();

        for (Payment payment : batch) {
           paymentMethods.add(payment.method());
        }
        return paymentMethods;
    }
}