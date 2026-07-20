package com.debuggeandoideas.api_collections.iterartor;

import com.debuggeandoideas.api_collections.dtos.Payment;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


public final class PaymentsIterable implements Iterable<Payment> {

    private final Payment[] payments;

    public PaymentsIterable(List<Payment> paymentsList) {
        this.payments = paymentsList.toArray(new Payment[0]);
    }

    @Override
    public Iterator<Payment> iterator() {
        return new PaymentIterator(this.payments);
    }

    private static final class PaymentIterator implements Iterator<Payment> {

        private final Payment[] payments;
        private int cursor = 0;

        private PaymentIterator(Payment[] payments) {
            this.payments = payments;
        }

        @Override
        public boolean hasNext() {
            return cursor < payments.length;
        }

        @Override
        public Payment next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return payments[cursor++];
        }
    }
}