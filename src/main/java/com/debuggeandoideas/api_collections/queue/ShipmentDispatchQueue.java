package com.debuggeandoideas.api_collections.queue;

import com.debuggeandoideas.api_collections.dtos.Shipment;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShipmentDispatchQueue {

    private final Queue<Shipment> pending = new PriorityQueue<>();

    public void enqueue(Shipment shipment) {
        throw new UnsupportedOperationException();
    }

    public Shipment dispatchNext() {
        throw new UnsupportedOperationException();
    }

    public boolean hasPending() {
        throw new UnsupportedOperationException();
    }
}
