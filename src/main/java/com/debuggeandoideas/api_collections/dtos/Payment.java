package com.debuggeandoideas.api_collections.dtos;

import java.time.Instant;

public record Payment(
        String reference,
        double amount,
        PaymentMethod method,
        Instant createdAt
) {
}