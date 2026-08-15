package com.debuggeandoideas.api_collections.tree;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class PaymentAmountIndex {

    private final SortedMap<Double, Payment> index = new TreeMap<>();

    public void add(Payment payment) {
       this.index.put(payment.amount(), payment);
    }

    public Map<Double, Payment> under(double amount) {
        return this.index.headMap(amount);
    }

    public Map<Double, Payment> atLeast(double amount) {
        return this.index.tailMap(amount);
    }

    public Map<Double, Payment> between(double low, double high) {
        return this.index.subMap(low, high);
    }
}