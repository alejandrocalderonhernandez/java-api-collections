package com.debuggeandoideas.api_collections.queue;

import com.debuggeandoideas.api_collections.dtos.Shipment;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ShipmentDispatchQueueTest {

    private Shipment shipment(String trackingNumber, double weight) {
        return new Shipment(trackingNumber, "Calle 1", weight, Instant.now());
    }

    @Test
    void hasPendingIsFalseWhenQueueIsEmpty() {
        ShipmentDispatchQueue queue = new ShipmentDispatchQueue();

        assertFalse(queue.hasPending());
    }

    @Test
    void hasPendingIsTrueAfterEnqueueing() {
        ShipmentDispatchQueue queue = new ShipmentDispatchQueue();

        queue.enqueue(shipment("TRK-001", 5.0));

        assertTrue(queue.hasPending());
    }

    @Test
    void dispatchesHeaviestFirst() {
        ShipmentDispatchQueue queue = new ShipmentDispatchQueue();
        queue.enqueue(shipment("TRK-001", 5.0));
        queue.enqueue(shipment("TRK-002", 20.0));
        queue.enqueue(shipment("TRK-003", 12.0));

        assertEquals("TRK-002", queue.dispatchNext().trackingNumber());
        assertEquals("TRK-003", queue.dispatchNext().trackingNumber());
        assertEquals("TRK-001", queue.dispatchNext().trackingNumber());
    }

    @Test
    void hasPendingIsFalseAfterDispatchingEverything() {
        ShipmentDispatchQueue queue = new ShipmentDispatchQueue();
        queue.enqueue(shipment("TRK-001", 5.0));

        queue.dispatchNext();

        assertFalse(queue.hasPending());
    }
}