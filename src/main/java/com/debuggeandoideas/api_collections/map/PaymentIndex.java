package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PaymentIndex {

    public Map<String, Payment> indexByReference(List<Payment> payments) {
        Map<String, Payment> index = new HashMap<>();

        for (Payment payment : payments) {
            index.put(payment.reference(), payment);
        }

        return index;
    }

    public Payment findByReference(Map<String, Payment> index, String reference) {
        return index.get(reference);
    }
}