package com.debuggeandoideas.api_collections.utils;

import com.debuggeandoideas.api_collections.dtos.*;
import net.datafaker.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DataFactory {

    private final Faker faker = new Faker();
    private long paymentSeq = 1L;
    private long mutablePaymentSeq = 1L;
    private long shipmentSeq = 1L;

    public Payment onePayment() {
        return new Payment(
                String.format("PAY-%08d", paymentSeq++),
                faker.number().randomDouble(2, 10, 5000),
                PaymentMethod.values()[faker.random().nextInt(PaymentMethod.values().length)],
                PaymentStatus.values()[faker.random().nextInt(PaymentStatus.values().length)],
                faker.timeAndDate().past(365, TimeUnit.DAYS)
        );
    }

    public List<Payment> payments(int n) {
        List<Payment> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(onePayment());
        }
        return result;
    }

    public MutablePayment oneMutablePayment() {
        return new MutablePayment(
                String.format("MPAY-%08d", mutablePaymentSeq++),
                faker.number().randomDouble(2, 10, 5000),
                PaymentMethod.values()[faker.random().nextInt(PaymentMethod.values().length)],
                PaymentStatus.values()[faker.random().nextInt(PaymentStatus.values().length)],
                faker.timeAndDate().past(365, TimeUnit.DAYS)
        );
    }

    public List<MutablePayment> mutablePayments(int n) {
        List<MutablePayment> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(oneMutablePayment());
        }
        return result;
    }

    public Shipment oneShipment() {
        return new Shipment(
                String.format("TRK-%09d", shipmentSeq++),
                faker.number().randomDouble(2, 1, 50),
                ShipmentStatus.values()[faker.random().nextInt(ShipmentStatus.values().length)],
                ShipmentPriority.values()[faker.random().nextInt(ShipmentPriority.values().length)],
                faker.timeAndDate().past(365, TimeUnit.DAYS)
        );
    }

    public List<Shipment> shipments(int n) {
        List<Shipment> result = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            result.add(oneShipment());
        }
        return result;
    }
}