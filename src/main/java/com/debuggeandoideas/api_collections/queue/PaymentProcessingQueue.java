package com.debuggeandoideas.api_collections.queue;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.LinkedList;
import java.util.Queue;

public final class PaymentProcessingQueue {

    private final Queue<Payment> pending = new LinkedList<>();

    public void enqueue(Payment payment) {
        throw new UnsupportedOperationException();
    }

    public Payment processNext() {
        throw new UnsupportedOperationException();
    }

    public Payment nextInLine() {
        throw new UnsupportedOperationException();
    }

    public boolean hasPending() {
        throw new UnsupportedOperationException();
    }
}