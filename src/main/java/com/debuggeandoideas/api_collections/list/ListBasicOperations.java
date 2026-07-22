package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.List;

/*
* CRUD With list
 */
public final class ListBasicOperations {

    private final List<Payment> payments;

    public ListBasicOperations(List<Payment> payments) {
        this.payments = payments;
    }

    public void insert(Payment payment, int index) {
        this.payments.add(index, payment);
    }

    public Payment get(int index) {
        return this.payments.get(index);
    }

    public void set(Payment payment, int index) {
        payments.set(index, payment);
    }

    public void print() {
        for (Payment payment : payments) {
            IO.println(payment);
        }
    }

    //🪲
    public void delete(Payment payment) {
       payments.remove(payment);

       //payments.removeIf(payment1 ->
       //        payment.method().equals(PaymentMethod.TRANSFER));

    }
}