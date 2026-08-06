package com.debuggeandoideas.api_collections.map;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.utils.AbstractCollectionTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PaymentIndexTest extends AbstractCollectionTest {

    private final PaymentIndex index = new PaymentIndex();

    @Test
    void indexByReferenceMapsEachPaymentToItsOwnReference() {
        List<Payment> data = payments(30);

        Map<String, Payment> indexed = index.indexByReference(data);

        assertEquals(data.size(), indexed.size());
        Payment sample = data.get(15);
        assertEquals(sample, indexed.get(sample.reference()));
    }

    @Test
    void findByReferenceReturnsTheMatchingPayment() {
        List<Payment> data = payments(30);
        Map<String, Payment> indexed = index.indexByReference(data);
        Payment target = data.get(7);

        Payment found = index.findByReference(indexed, target.reference());

        assertEquals(target, found);
    }

    @Test
    void findByReferenceReturnsNullWhenReferenceDoesNotExist() {
        List<Payment> data = payments(30);
        Map<String, Payment> indexed = index.indexByReference(data);

        Payment found = index.findByReference(indexed, "PAY-DOES-NOT-EXIST");

        assertNull(found);
    }
}