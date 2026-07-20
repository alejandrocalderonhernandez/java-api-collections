package com.debuggeandoideas.api_collections.dtos;

import java.time.Instant;
import java.util.Objects;

public class MutablePayment {

    private final String reference;
    private double amount;
    private PaymentMethod method;
    private final Instant createdAt;

    public MutablePayment(String reference, double amount, PaymentMethod method,
                           Instant createdAt) {
        this.reference = reference;
        this.amount = amount;
        this.method = method;
        this.createdAt = createdAt;
    }

    public String getReference() {
        return reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MutablePayment other)) return false;
        return reference.equals(other.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reference);
    }

    @Override
    public String toString() {
        return "MutablePayment[reference=" + reference + ", amount=" + amount
                + "]";
    }
}