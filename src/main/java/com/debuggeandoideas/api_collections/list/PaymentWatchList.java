

package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.ArrayList;
import java.util.List;

public final class PaymentWatchList {

    private final List<Payment> watched = new ArrayList<>();

    public void watch(Payment payment) {
        throw new UnsupportedOperationException();
    }

    public void stopWatching(Payment payment) {
        throw new UnsupportedOperationException();
    }

    public void addRelatedIfFound(Payment trigger, Payment related) {
        throw new UnsupportedOperationException();
    }

    public List<Payment> currentlyWatched() {
        throw new UnsupportedOperationException();
    }
}