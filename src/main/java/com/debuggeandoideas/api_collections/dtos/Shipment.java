package com.debuggeandoideas.api_collections.dtos;

import java.time.Instant;

public record Shipment(
        String trackingNumber,
        double weight,
        ShipmentStatus status,
        ShipmentPriority priority,
        Instant createdAt
) {
}