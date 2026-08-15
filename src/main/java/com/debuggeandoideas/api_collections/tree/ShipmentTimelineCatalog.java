package com.debuggeandoideas.api_collections.tree;

import com.debuggeandoideas.api_collections.dtos.Shipment;

import java.time.Instant;

public final class ShipmentTimelineCatalog {


    public void add(Shipment shipment) {
        throw new UnsupportedOperationException();
    }

    public Shipment earliest() {
        throw new UnsupportedOperationException();
    }

    public Shipment latest() {
        throw new UnsupportedOperationException();
    }

    public Shipment closestBeforeOrEqual(Instant date) {
        throw new UnsupportedOperationException();
    }

    public Shipment closestAfterOrEqual(Instant date) {
        throw new UnsupportedOperationException();
    }

    private Shipment probe(Instant date) {
        return new Shipment("PROBE", "", 0.0, date);
    }
}