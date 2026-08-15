package com.debuggeandoideas.api_collections.concurrency;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class PaymentAmountCache {

    private static final Map<String, Double> MEM_CACHE = new ConcurrentHashMap<>();

    private static final AtomicInteger DB_HITS = new AtomicInteger();

    public void post(Payment payment) {
        this.savePayment(payment);
        MEM_CACHE.put(payment.reference(), payment.amount());
    }

    public Double get(String reference) {
        return MEM_CACHE.computeIfAbsent(reference, ref
                -> this.getByReference(ref).amount());
    }

    public int size() {
        return MEM_CACHE.size();
    }

    public int dbHits() {
        return DB_HITS.get();
    }

    private void savePayment(Payment payment) {
        System.out.println("Saving payment " + payment);
    }

    private Payment getByReference(String reference) {
        DB_HITS.incrementAndGet();
        return new Payment(reference, 100.0, PaymentMethod.CARD, Instant.now()); // dummy
    }
}