

package com.debuggeandoideas.api_collections.tree;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class PaymentAmountIndex {

    private final SortedMap<Double, Payment> index = new TreeMap<>();

    public void add(Payment payment) {
        throw new UnsupportedOperationException();
    }

    public Map<Double, Payment> under(double amount) {
        throw new UnsupportedOperationException();
    }

    public Map<Double, Payment> atLeast(double amount) {
        throw new UnsupportedOperationException();
    }

    public Map<Double, Payment> between(double low, double high) {
        throw new UnsupportedOperationException();
    }
}