package com.debuggeandoideas.api_collections.queue;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaymentProcessingQueueTest {

    private Payment payment(String reference) {
        return new Payment(reference, 100.0, PaymentMethod.CARD, Instant.now());
    }

    @Test
    void hasPendingIsFalseWhenQueueIsEmpty() {
        PaymentProcessingQueue queue = new PaymentProcessingQueue();

        assertFalse(queue.hasPending());
    }

    @Test
    void hasPendingIsTrueAfterEnqueueing() {
        PaymentProcessingQueue queue = new PaymentProcessingQueue();

        queue.enqueue(payment("PAY-001"));

        assertTrue(queue.hasPending());
    }

    @Test
    void nextInLineShowsTheOldestPaymentWithoutRemovingIt() {
        PaymentProcessingQueue queue = new PaymentProcessingQueue();
        queue.enqueue(payment("PAY-001"));
        queue.enqueue(payment("PAY-002"));

        Payment next = queue.nextInLine();

        assertEquals("PAY-001", next.reference());
        assertTrue(queue.hasPending()); // sigue ahí, no se quitó
    }

    @Test
    void processNextReturnsPaymentsInTheOrderTheyArrived() {
        PaymentProcessingQueue queue = new PaymentProcessingQueue();
        queue.enqueue(payment("PAY-001"));
        queue.enqueue(payment("PAY-002"));
        queue.enqueue(payment("PAY-003"));

        assertEquals("PAY-001", queue.processNext().reference());
        assertEquals("PAY-002", queue.processNext().reference());
        assertEquals("PAY-003", queue.processNext().reference());
    }

    @Test
    void processNextReturnsNullWhenQueueIsEmpty() {
        PaymentProcessingQueue queue = new PaymentProcessingQueue();

        assertNull(queue.processNext());
    }
}