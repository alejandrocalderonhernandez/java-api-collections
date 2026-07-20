package com.debuggeandoideas.api_collections.iterartor;


import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.Iterator;
import java.util.List;

public final class PaymentsIterable implements Iterable<Payment> {

    private final Payment[] payments;

    public PaymentsIterable(List<Payment> payments) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<Payment> iterator() {
        throw new UnsupportedOperationException();
    }

    private static final class PaymentIterator implements Iterator<Payment> {

        private final Payment[] payments;
        private int cursor = 0;
        private Payment nextPayment;

        private PaymentIterator(Payment[] payments) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException();
        }

        @Override
        public Payment next() {
            throw new UnsupportedOperationException();
        }

        private void advance() {
            throw new UnsupportedOperationException();
        }
    }
}