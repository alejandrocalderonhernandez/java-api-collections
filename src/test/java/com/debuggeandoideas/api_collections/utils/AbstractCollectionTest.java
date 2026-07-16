package com.debuggeandoideas.api_collections.utils;

import com.debuggeandoideas.api_collections.dtos.MutablePayment;
import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.Shipment;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractCollectionTest {

    protected final DataFactory factory = new DataFactory();

    protected List<Payment> payments(int n) {
        return factory.payments(n);
    }

    protected List<Shipment> shipments(int n) {
        return factory.shipments(n);
    }

    protected List<MutablePayment> mutablePayments(int n) {
        return factory.mutablePayments(n);
    }

    protected Set<Payment> paymentsAsSet(int n) {
        return new HashSet<>(payments(n));
    }

    protected Set<Shipment> shipmentsAsSet(int n) {
        return new HashSet<>(shipments(n));
    }

    protected Map<String, Payment> paymentsAsMap(int n) {
        Map<String, Payment> map = new HashMap<>();
        for (Payment payment : payments(n)) {
            map.put(payment.reference(), payment);
        }
        return map;
    }

    protected Map<String, Shipment> shipmentsAsMap(int n) {
        Map<String, Shipment> map = new HashMap<>();
        for (Shipment shipment : shipments(n)) {
            map.put(shipment.trackingNumber(), shipment);
        }
        return map;
    }

    protected long time(Runnable operation) {
        return StopWatch.measure(operation);
    }

    protected long time(String label, Runnable operation) {
        return StopWatch.measureAndPrint(label, operation);
    }
}
