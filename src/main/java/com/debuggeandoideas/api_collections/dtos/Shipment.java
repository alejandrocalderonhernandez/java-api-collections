package com.debuggeandoideas.api_collections.dtos;

import java.time.Instant;

public record Shipment(
        String trackingNumber,
        String address,
        double weight,
        Instant createdAt
) {
}