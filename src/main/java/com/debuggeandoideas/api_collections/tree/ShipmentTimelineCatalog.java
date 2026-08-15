package com.debuggeandoideas.api_collections.tree;

import com.debuggeandoideas.api_collections.dtos.Shipment;

import java.time.Instant;
import java.util.*;

public final class ShipmentTimelineCatalog {

    private static final Comparator<Shipment> CREATED_BY_COMPARING =
            Comparator.comparing(Shipment::createdAt);

    private final NavigableSet<Shipment> shipments =
            new TreeSet<>(CREATED_BY_COMPARING);

    public void add(Shipment shipment) {
        this.shipments.add(shipment);
    }

    public Shipment earliest() {
        return shipments.first();
    }

    public Shipment latest() {
        return shipments.last();
    }

    public Shipment closestBeforeOrEqual(Instant date) {
        return this.shipments.floor(this.probe(date));
    }

    public Shipment closestAfterOrEqual(Instant date) {
        return this.shipments.ceiling(this.probe(date));
    }

    private Shipment probe(Instant date) {
        return new Shipment("PROBE", "", 0.0, date);
    }
}