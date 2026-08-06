package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.List;
import java.util.Map;

public final class PaymentIndex {

    public Map<String, Payment> indexByReference(List<Payment> payments) {
        throw new UnsupportedOperationException();
    }

    public Payment findByReference(Map<String, Payment> index, String reference) {
        throw new UnsupportedOperationException();
    }
}