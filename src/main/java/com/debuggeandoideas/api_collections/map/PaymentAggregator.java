package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;

import java.util.List;
import java.util.Map;

public final class PaymentAggregator {

    public Map<PaymentMethod, Double> totalAmountByMethod(List<Payment> payments) {
        throw new UnsupportedOperationException();
    }

    public Map<PaymentMethod, Integer> countByMethod(List<Payment> payments) {
        throw new UnsupportedOperationException();
    }
}