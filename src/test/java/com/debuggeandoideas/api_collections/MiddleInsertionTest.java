package com.debuggeandoideas.api_collections;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.utils.AbstractCollectionTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("ArrayList vs LinkedList: insert in the middle")
class MiddleInsertionTest extends AbstractCollectionTest {

    private static final int SIZE = 2000;
    private static final int INSERTIONS = 2000;

    @Test
    @DisplayName("insert 2000 elements in the middle of a 2000-element list")
    void insertInMiddle() {
        List<Payment> data = payments(SIZE);
        Payment sample = data.get(0);

        List<Payment> arrayList = new ArrayList<>(data);
        List<Payment> linkedList = new LinkedList<>(data);

        time("ArrayList middle insert", () -> {
            for (int i = 0; i < INSERTIONS; i++) {
                arrayList.add(arrayList.size() / 2, sample);
            }
        });

        time("LinkedList middle insert", () -> {
            for (int i = 0; i < INSERTIONS; i++) {
                linkedList.add(linkedList.size() / 2, sample);
            }
        });

        assertEquals(SIZE + INSERTIONS, arrayList.size());
        assertEquals(SIZE + INSERTIONS, linkedList.size());
    }
}