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
        throw new UnsupportedOperationException();
    }

    public boolean isDuplicate(Payment payment) {
        throw new UnsupportedOperationException();
    }

    public Set<PaymentMethod> distinctPaymentMethods(List<Payment> batch) {
        throw new UnsupportedOperationException();
    }
}