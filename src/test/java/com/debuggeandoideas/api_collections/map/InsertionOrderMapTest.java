package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Shipment;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertionOrderMapTest {

    private final InsertionOrderMap insertionOrderMap = new InsertionOrderMap();

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
    void hashMapKeepsInsertionOrder() {
        List<Shipment> insertionOrder = orderedShipments();

        Map<String, Shipment> result = insertionOrderMap.insertIntoHashMap(insertionOrder);

        List<Shipment> iterationOrder = new ArrayList<>(result.values());
        assertEquals(insertionOrder, iterationOrder);
    }
}