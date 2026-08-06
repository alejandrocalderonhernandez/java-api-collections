package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PaymentAggregator {

    public Map<PaymentMethod, Double> totalAmountByMethod(List<Payment> payments) {
        Map<PaymentMethod, Double> totalAmountByMethod = new HashMap<>();
        for (Payment payment : payments) {
            totalAmountByMethod.merge(payment.method(), payment.amount(), Double::sum);
        }
        return totalAmountByMethod;
    }

    public Map<PaymentMethod, Integer> countByMethod(List<Payment> payments) {
        Map<PaymentMethod, Integer> countByMethod = new HashMap<>();
        for (Payment payment : payments) {
            //countByMethod.merge(payment.method(), 1, Integer::sum);

            countByMethod.put(payment.method(),
                    countByMethod.getOrDefault(payment.method(), 0) + 1);

        }

        return countByMethod;
    }
}