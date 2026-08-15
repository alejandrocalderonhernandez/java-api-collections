package com.debuggeandoideas.api_collections.concurrency;

import com.debuggeandoideas.api_collections.dtos.MutablePayment;

import java.util.ArrayList;
import java.util.List;

public final class PaymentLog {

    private final List<MutablePayment> payments = new ArrayList<>();

    public void record(MutablePayment payment) {
        payments.add(payment);
    }

    public int count() {
        return payments.size();
    }
}