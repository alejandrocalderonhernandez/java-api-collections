package com.debuggeandoideas.api_collections.concurrency;

import com.debuggeandoideas.api_collections.dtos.MutablePayment;

import java.util.HashSet;
import java.util.Set;

public final class ProcessedPaymentSet {

    private final Set<MutablePayment> processed = new HashSet<>(); // 🪲

    public void markProcessed(MutablePayment payment) {
        processed.add(payment);

    }

    public int count() {
        return processed.size();
    }
}