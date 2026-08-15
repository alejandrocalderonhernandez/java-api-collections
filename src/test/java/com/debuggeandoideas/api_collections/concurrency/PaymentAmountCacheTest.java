package com.debuggeandoideas.api_collections.concurrency;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PaymentAmountCacheTest {

    private static final int THREADS = 20;

    private String uniqueReference() {
        return "PAY-" + UUID.randomUUID();
    }

    @Test
    void postThenGetReturnsTheCachedAmount() {
        PaymentAmountCache cache = new PaymentAmountCache();
        String reference = uniqueReference();
        Payment payment = new Payment(reference, 250.0, PaymentMethod.CARD, Instant.now());

        cache.post(payment);

        assertEquals(250.0, cache.get(reference));
    }

    @Test
    void getOnAMissingReferenceFallsBackToTheDatabase() {
        PaymentAmountCache cache = new PaymentAmountCache();
        String reference = uniqueReference(); // nunca se guardó con post()

        Double result = cache.get(reference);

        assertNotNull(result); // lo trajo de la "BD" (getByReference), no regresó null
    }

    @Test
    @Timeout(5)
    void getForTheSameMissingReferenceFromManyThreadsHitsTheDatabaseOnlyOnce()
            throws InterruptedException, ExecutionException {
        PaymentAmountCache cache = new PaymentAmountCache();
        String reference = uniqueReference();
        int hitsBefore = cache.dbHits();

        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        CountDownLatch startSignal = new CountDownLatch(1);

        List<Future<?>> futures = new ArrayList<>();
        for (int t = 0; t < THREADS; t++) {
            futures.add(executor.submit(() -> {
                try {
                    startSignal.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                cache.get(reference); // los 20 hilos piden LA MISMA referencia
            }));
        }

        startSignal.countDown();
        for (Future<?> future : futures) {
            future.get();
        }
        executor.shutdown();

        assertEquals(1, cache.dbHits() - hitsBefore); // la "BD" solo debió consultarse UNA vez
    }
}