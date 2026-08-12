package com.debuggeandoideas.api_collections.queue;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.LinkedList;
import java.util.Queue;

public final class PaymentProcessingQueue {

    private final Queue<Payment> pending = new LinkedList<>();

    public void enqueue(Payment payment) {
        this.pending.offer(payment);
    }

    public Payment processNext() {
        return this.pending.poll();
    }

    public Payment nextInLine() {
        return this.pending.poll();
    }

    public boolean hasPending() {
        return !this.pending.isEmpty();
    }
}