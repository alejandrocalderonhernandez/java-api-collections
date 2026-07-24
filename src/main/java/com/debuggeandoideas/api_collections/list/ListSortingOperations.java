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
       this.payments = payments;
    }

    public Comparator<Payment> byAmount() {
        return Comparator.comparingDouble(Payment::amount);
    }

    public void sortByAmount() {
        this.payments.sort(this.byAmount());
    }

    public int binarySearchByAmount(double amount) {

        return Collections.binarySearch(
                this.payments,
                new Payment("SEARCH_KEY", amount, PaymentMethod.CARD, Instant.now()),
                this.byAmount()
        );
    }
}