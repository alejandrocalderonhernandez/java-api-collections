package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Shipment;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class InsertionOrderMap {

    public Map<String, Shipment> insertIntoHashMap(List<Shipment> shipments) {
        Map<String, Shipment> map = new LinkedHashMap<>();
        for (Shipment shipment : shipments) {
            map.put(shipment.trackingNumber(), shipment);
        }
        return map;
    }
}