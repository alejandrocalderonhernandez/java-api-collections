package com.debuggeandoideas.api_collections.stack;

import com.debuggeandoideas.api_collections.dtos.ShipmentStatus;

import java.util.ArrayDeque;
import java.util.Deque;

public final class ShipmentStatusHistory {

    private final Deque<ShipmentStatus> history = new ArrayDeque<>();

    public void recordChange(ShipmentStatus newStatus) {
        throw new UnsupportedOperationException();
    }

    public ShipmentStatus undoLastChange() {
        throw new UnsupportedOperationException();
    }

    public ShipmentStatus currentStatus() {
        throw new UnsupportedOperationException();
    }
}