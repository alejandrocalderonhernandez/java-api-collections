package com.debuggeandoideas.api_collections.iteartor;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.iterartor.PaymentsIterable;
import com.debuggeandoideas.api_collections.utils.AbstractCollectionTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentsIterableTest extends AbstractCollectionTest {

    @Test
    void iteratesOverAllPayments() {
        List<Payment> data = payments(20);
        PaymentsIterable paymentsIterable = new PaymentsIterable(data);

        int count = 0;
        for (Payment payment : paymentsIterable) {
            count++;
        }

        assertEquals(data.size(), count);
    }
}