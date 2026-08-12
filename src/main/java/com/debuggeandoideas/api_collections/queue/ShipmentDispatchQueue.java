package com.debuggeandoideas.api_collections.queue;

import com.debuggeandoideas.api_collections.dtos.Shipment;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class ShipmentDispatchQueue {

    private final Queue<Shipment> pending = new PriorityQueue<>(
            Comparator.comparingDouble(Shipment::weight).reversed()
    );

    public void enqueue(Shipment shipment) {
        this.pending.offer(shipment);
    }

    public Shipment dispatchNext() {
        return this.pending.poll();
    }

    public boolean hasPending() {
        return !this.pending.isEmpty();
    }
}
