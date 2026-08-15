package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class PaymentWatchList {

    private final List<Payment> watched = new CopyOnWriteArrayList<>();

    public void watch(Payment payment) {
        watched.add(payment);
    }

    public void stopWatching(Payment payment) {
        for (Payment current : watched) {
            if (current.equals(payment)) {
                watched.remove(current);
            }
        }
    }

    public void addRelatedIfFound(Payment trigger, Payment related) {
        for (Payment current : watched) {
            if (current.equals(trigger)) {
                watched.add(related);
            }
        }
    }

    public List<Payment> currentlyWatched() {
        return watched;
    }
}