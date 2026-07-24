package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.dtos.PaymentMethod;
import com.debuggeandoideas.api_collections.utils.AbstractCollectionTest;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ListSortingOperationsTest extends AbstractCollectionTest {

    @Test
    void byAmountOrdersCheaperPaymentFirst() {
        ListSortingOperations operations = new ListSortingOperations(new ArrayList<>());
        Comparator<Payment> comparator = operations.byAmount();

        Payment cheap = new Payment("PAY-CHEAP", 10.0, PaymentMethod.CARD, Instant.now());
        Payment expensive = new Payment("PAY-EXPENSIVE", 50.0, PaymentMethod.CARD, Instant.now());

        assertTrue(comparator.compare(cheap, expensive) < 0);
        assertTrue(comparator.compare(expensive, cheap) > 0);
        assertEquals(0, comparator.compare(cheap, cheap));
    }

    @Test
    void sortByAmountLeavesListInAscendingOrder() {
        List<Payment> data = payments(30);
        ListSortingOperations operations = new ListSortingOperations(data);

        operations.sortByAmount();

        for (int i = 0; i < data.size() - 1; i++) {
            assertTrue(data.get(i).amount() <= data.get(i + 1).amount());
        }
    }

    @Test
    void binarySearchByAmountFindsAnExistingPayment() {
        List<Payment> data = payments(30);
        ListSortingOperations operations = new ListSortingOperations(data);
        operations.sortByAmount();

        Payment target = data.get(15);

        int index = operations.binarySearchByAmount(target.amount());

        assertEquals(target.amount(), data.get(index).amount());
    }

    @Test
    void binarySearchByAmountReturnsNegativeWhenNotFound() {
        List<Payment> data = payments(30);
        ListSortingOperations operations = new ListSortingOperations(data);
        operations.sortByAmount();

        double impossibleAmount = data.get(data.size() - 1).amount() + 10_000;

        int index = operations.binarySearchByAmount(impossibleAmount);

        assertTrue(index < 0);
    }
}