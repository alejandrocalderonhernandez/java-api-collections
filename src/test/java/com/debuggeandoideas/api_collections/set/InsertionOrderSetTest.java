package com.debuggeandoideas.api_collections.set;
import com.debuggeandoideas.api_collections.dtos.Shipment;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertionOrderSetTest {

    private final InsertionOrderSet insertionOrderSet = new InsertionOrderSet();

    private List<Shipment> orderedShipments() {
        List<Shipment> shipments = new ArrayList<>();
        shipments.add(new Shipment("TRK-001", "Calle 1", 1.0, Instant.now()));
        shipments.add(new Shipment("TRK-002", "Calle 2", 2.0, Instant.now()));
        shipments.add(new Shipment("TRK-003", "Calle 3", 3.0, Instant.now()));
        shipments.add(new Shipment("TRK-004", "Calle 4", 4.0, Instant.now()));
        shipments.add(new Shipment("TRK-005", "Calle 5", 5.0, Instant.now()));
        return shipments;
    }

    @Test
    void hashSetKeepsInsertionOrder() {
        List<Shipment> insertionOrder = orderedShipments();

        Set<Shipment> result = insertionOrderSet.insertIntoHashSet(insertionOrder);

        List<Shipment> iterationOrder = new ArrayList<>(result);
        assertEquals(insertionOrder, iterationOrder);
    }
}