package com.debuggeandoideas.api_collections.concurrency;

import com.debuggeandoideas.api_collections.dtos.MutablePayment;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public final class ProcessedPaymentSet {

    private final Set<MutablePayment> processed = new CopyOnWriteArraySet<>(); // 🪲

    public void markProcessed(MutablePayment payment) {
        processed.add(payment);

    }

    public int count() {
        return processed.size();
    }
}