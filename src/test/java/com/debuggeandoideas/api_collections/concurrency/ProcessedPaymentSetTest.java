package com.debuggeandoideas.api_collections.concurrency;

import com.debuggeandoideas.api_collections.dtos.MutablePayment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProcessedPaymentSetTest {

    private static final int THREADS = 20;
    private static final int RECORDS_PER_THREAD = 1000;

    @Test
    @Timeout(5)
    void markingFromManyThreadsAtOnceCrashesOrHangs() throws InterruptedException, ExecutionException {
        ProcessedPaymentSet set = new ProcessedPaymentSet();
        ExecutorService executor = Executors.newFixedThreadPool(THREADS);
        CountDownLatch startSignal = new CountDownLatch(1);

        List<Future<?>> futures = new ArrayList<>();
        for (int t = 0; t < THREADS; t++) {
            final int threadId = t;
            futures.add(executor.submit(() -> {
                try {
                    startSignal.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                for (int i = 0; i < RECORDS_PER_THREAD; i++) {
                    MutablePayment payment = new MutablePayment(
                            "PAY-" + threadId + "-" + i, 100.0, PaymentMethod.CARD, Instant.now());
                    set.markProcessed(payment);
                }
            }));
        }

        startSignal.countDown();

        for (Future<?> future : futures) {
            future.get();
        }
        executor.shutdown();

        assertEquals(THREADS * RECORDS_PER_THREAD, set.count());
    }
}