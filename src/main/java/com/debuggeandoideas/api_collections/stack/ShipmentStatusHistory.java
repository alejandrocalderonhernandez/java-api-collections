package com.debuggeandoideas.api_collections.stack;

import com.debuggeandoideas.api_collections.dtos.ShipmentStatus;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ShipmentStatusHistory {

    private final Deque<ShipmentStatus> history = new ArrayDeque<>();

    public void recordChange(ShipmentStatus newStatus) {
        this.history.push(newStatus);
    }

    public ShipmentStatus undoLastChange() {
        return this.history.pop();
    }

    public ShipmentStatus currentStatus() {
        return this.history.peek();
    }
}