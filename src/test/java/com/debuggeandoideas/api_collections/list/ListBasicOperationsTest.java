package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.utils.AbstractCollectionTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ListBasicOperationsTest extends AbstractCollectionTest {

    @Test
    void insertsPaymentAtGivenIndex() {
        List<Payment> data = payments(10);
        ListBasicOperations operations = new ListBasicOperations(data);
        Payment newPayment = payments(1).get(0);

        operations.insert(newPayment, 3);

        assertEquals(11, data.size());
        assertEquals(newPayment, operations.get(3));
    }

    @Test
    void getsPaymentAtGivenIndex() {
        List<Payment> data = payments(10);
        ListBasicOperations operations = new ListBasicOperations(data);

        assertEquals(data.get(5), operations.get(5));
    }

    @Test
    void setReplacesPaymentWithoutChangingSize() {
        List<Payment> data = payments(10);
        ListBasicOperations operations = new ListBasicOperations(data);
        Payment replacement = payments(1).get(0);

        operations.set(replacement, 4);

        assertEquals(10, data.size());
        assertEquals(replacement, operations.get(4));
    }

    @Test
    void printDoesNotThrow() {
        List<Payment> data = payments(5);
        ListBasicOperations operations = new ListBasicOperations(data);

        assertDoesNotThrow(operations::print);
    }

    @Test
    void deletingWhileIteratingCrashes() {
        List<Payment> data = payments(10);
        ListBasicOperations operations = new ListBasicOperations(data);

        operations.delete(data.get(3));
    }
}