package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.List;

/*
* CRUD With list
 */
public final class ListBasicOperations {

    private final List<Payment> payments;

    public ListBasicOperations(List<Payment> payments) {
        throw new UnsupportedOperationException();
    }

    public void insert(Payment payment, int index) {
        throw new UnsupportedOperationException();
    }

    public Payment get(int index) {
        throw new UnsupportedOperationException();
    }

    public void set(Payment payment, int index) {
        throw new UnsupportedOperationException();
    }

    public void print() {
        throw new UnsupportedOperationException();
    }

    //🪲
    public void delete(Payment payment) {
        for (Payment current : payments) {
            if (current.equals(payment)) {
                payments.remove(current);
            }
        }
    }
}