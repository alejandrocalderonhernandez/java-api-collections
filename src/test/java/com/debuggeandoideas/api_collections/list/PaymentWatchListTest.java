package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class PaymentWatchListTest {

    private Payment payment(String reference) {
        return new Payment(reference, 100.0, PaymentMethod.CARD, Instant.now());
    }

    @Test
    void stopWatchingWhileIteratingCrashes() {
        PaymentWatchList watchList = new PaymentWatchList();
        Payment first = payment("PAY-001");
        watchList.watch(first);
        watchList.watch(payment("PAY-002"));
        watchList.watch(payment("PAY-003"));

        watchList.stopWatching(first); // se quita el PRIMERO — truena de forma confiable
    }

    @Test
    void addRelatedWhileIteratingCrashes() {
        PaymentWatchList watchList = new PaymentWatchList();
        Payment trigger = payment("PAY-001");
        watchList.watch(trigger);
        watchList.watch(payment("PAY-002"));

        watchList.addRelatedIfFound(trigger, payment("PAY-999")); // add crece el tamaño — siempre truena
    }
}