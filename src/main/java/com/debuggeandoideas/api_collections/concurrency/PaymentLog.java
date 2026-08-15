package com.debuggeandoideas.api_collections.concurrency;

import com.debuggeandoideas.api_collections.dtos.MutablePayment;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class PaymentLog {

    private final List<MutablePayment> payments = new CopyOnWriteArrayList<>();

    public void record(MutablePayment payment) {
        payments.add(payment);
    }

    public int count() {
        return payments.size();
    }
}