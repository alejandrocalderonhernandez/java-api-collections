package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class ListSortingOperations {

    private final List<Payment> payments;

    public ListSortingOperations(List<Payment> payments) {
        throw new UnsupportedOperationException();
    }

    public Comparator<Payment> byAmount() {
        throw new UnsupportedOperationException();
    }

    public void sortByAmount() {
        throw new UnsupportedOperationException();
    }

    public int binarySearchByAmount(double amount) {
        throw new UnsupportedOperationException();
    }
}