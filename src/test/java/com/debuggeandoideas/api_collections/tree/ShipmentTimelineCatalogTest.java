package com.debuggeandoideas.api_collections.tree;

import com.debuggeandoideas.api_collections.dtos.Shipment;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ShipmentTimelineCatalogTest {

    private final Instant day1 = Instant.parse("2026-01-01T00:00:00Z");
    private final Instant day2 = Instant.parse("2026-01-05T00:00:00Z");
    private final Instant day3 = Instant.parse("2026-01-10T00:00:00Z");

    private Shipment shipment(String trackingNumber, Instant createdAt) {
        return new Shipment(trackingNumber, "Calle 1", 1.0, createdAt);
    }

    private ShipmentTimelineCatalog buildCatalog() {
        ShipmentTimelineCatalog catalog = new ShipmentTimelineCatalog();
        catalog.add(shipment("TRK-001", day1));
        catalog.add(shipment("TRK-002", day2));
        catalog.add(shipment("TRK-003", day3));
        return catalog;
    }

    @Test
    void earliestReturnsTheOldestShipment() {
        assertEquals("TRK-001", buildCatalog().earliest().trackingNumber());
    }

    @Test
    void latestReturnsTheMostRecentShipment() {
        assertEquals("TRK-003", buildCatalog().latest().trackingNumber());
    }

    @Test
    void closestBeforeOrEqualFindsExactMatch() {
        assertEquals("TRK-002", buildCatalog().closestBeforeOrEqual(day2).trackingNumber());
    }

    @Test
    void closestAfterOrEqualFindsNearestLaterWhenNoExactMatch() {
        Instant between1and2 = Instant.parse("2026-01-03T00:00:00Z");

        assertEquals("TRK-002", buildCatalog().closestAfterOrEqual(between1and2).trackingNumber());
    }

    @Test
    void closestBeforeOrEqualReturnsNullWhenNothingQualifies() {
        Instant beforeEverything = Instant.parse("2025-01-01T00:00:00Z");

        assertNull(buildCatalog().closestBeforeOrEqual(beforeEverything));
    }
}