package com.debuggeandoideas.api_collections.set;

import com.debuggeandoideas.api_collections.dtos.Shipment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class InsertionOrderSet {

    public Set<Shipment> insertIntoHashSet(List<Shipment> shipments) {
        Set<Shipment> set = new HashSet<>();
        set.addAll(shipments);
        return set;
    }
}