package com.debuggeandoideas.api_collections.list;

import com.debuggeandoideas.api_collections.dtos.Payment;
import com.debuggeandoideas.api_collections.utils.AbstractCollectionTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class ListPerformanceTest extends AbstractCollectionTest {

    private static final int SIZE = 150_000;
    private static final int GET_SET_REPETITIONS = 60_000;
    private static final int INSERT_REPETITIONS = 60_000;
    private static final int DELETE_REPETITIONS = 30_000;
    private static final int QUEUE_REPETITIONS = 40_000;
    private static final int RESIZE_OPERATIONS = 500_000;

    private List<Payment> seedData;

    private List<Payment> freshArrayList() {
        return new ArrayList<>(seedData);

    }

    private List<Payment> freshLinkedList() {
        return new LinkedList<>(seedData);
    }

    @Test
    void comparesArrayListVsLinkedList() {
        seedData = payments(SIZE);
        int middleIndex = SIZE / 2;
        Payment replacement = payments(1).get(0);

        // get
        List<Payment> arrayListForGet = freshArrayList();
        List<Payment> linkedListForGet = freshLinkedList();
        ListBasicOperations arrayListGetOps = new ListBasicOperations(arrayListForGet);
        ListBasicOperations linkedListGetOps = new ListBasicOperations(linkedListForGet);

        System.out.println();
        System.out.println("===== GET AT MIDDLE INDEX =====");
        double arrayListGetMs = time("ArrayList  get(middle)", () -> {
            double checksum = 0;
            for (int i = 0; i < GET_SET_REPETITIONS; i++) {
                checksum += arrayListGetOps.get(middleIndex).amount();
            }
            System.out.println("   (checksum: " + checksum + ")");
        });
        double linkedListGetMs = time("LinkedList get(middle)", () -> {
            double checksum = 0;
            for (int i = 0; i < GET_SET_REPETITIONS; i++) {
                checksum += linkedListGetOps.get(middleIndex).amount();
            }
            System.out.println("   (checksum: " + checksum + ")");
        });
        System.out.println("Winner: " + (arrayListGetMs < linkedListGetMs ? "ArrayList" : "LinkedList"));

        // set
        List<Payment> arrayListForSet = freshArrayList();
        List<Payment> linkedListForSet = freshLinkedList();
        ListBasicOperations arrayListSetOps = new ListBasicOperations(arrayListForSet);
        ListBasicOperations linkedListSetOps = new ListBasicOperations(linkedListForSet);

        System.out.println();
        System.out.println("===== SET AT MIDDLE INDEX =====");
        double arrayListSetMs = time("ArrayList  set(middle)", () -> {
            for (int i = 0; i < GET_SET_REPETITIONS; i++) {
                arrayListSetOps.set(replacement, middleIndex);
            }
        });
        double linkedListSetMs = time("LinkedList set(middle)", () -> {
            for (int i = 0; i < GET_SET_REPETITIONS; i++) {
                linkedListSetOps.set(replacement, middleIndex);
            }
        });
        System.out.println("Winner: " + (arrayListSetMs < linkedListSetMs ? "ArrayList" : "LinkedList"));

        // insert (principio)
        List<Payment> arrayListForInsert = freshArrayList();
        List<Payment> linkedListForInsert = freshLinkedList();
        ListBasicOperations arrayListInsertOps = new ListBasicOperations(arrayListForInsert);
        ListBasicOperations linkedListInsertOps = new ListBasicOperations(linkedListForInsert);

        System.out.println();
        System.out.println("===== INSERT AT THE BEGINNING (index 0) =====");
        double arrayListInsertMs = time("ArrayList  insert(0)", () -> {
            for (int i = 0; i < INSERT_REPETITIONS; i++) {
                arrayListInsertOps.insert(replacement, 0);
            }
        });
        double linkedListInsertMs = time("LinkedList insert(0)", () -> {
            for (int i = 0; i < INSERT_REPETITIONS; i++) {
                linkedListInsertOps.insert(replacement, 0);
            }
        });
        System.out.println("Winner: " + (arrayListInsertMs < linkedListInsertMs ? "ArrayList" : "LinkedList"));

        // delete
        List<Payment> arrayListForDelete = freshArrayList();
        List<Payment> linkedListForDelete = freshLinkedList();
        ListBasicOperations arrayListDeleteOps = new ListBasicOperations(arrayListForDelete);
        ListBasicOperations linkedListDeleteOps = new ListBasicOperations(linkedListForDelete);

        System.out.println();
        System.out.println("===== DELETE BY VALUE =====");
        double arrayListDeleteMs = time("ArrayList  delete(value)", () -> {
            for (int i = 0; i < DELETE_REPETITIONS; i++) {
                Payment target = arrayListForDelete.get(arrayListForDelete.size() / 2);
                arrayListDeleteOps.delete(target);
            }
        });
        double linkedListDeleteMs = time("LinkedList delete(value)", () -> {
            for (int i = 0; i < DELETE_REPETITIONS; i++) {
                Payment target = linkedListForDelete.get(linkedListForDelete.size() / 2);
                linkedListDeleteOps.delete(target);
            }
        });
        System.out.println("Winner: " + (arrayListDeleteMs < linkedListDeleteMs ? "ArrayList" : "LinkedList"));

        // cruce (queue: se usa la punta de enfrente y la de atrás a la vez)
        List<Payment> arrayListQueue = freshArrayList();
        List<Payment> linkedListQueue = freshLinkedList();

        System.out.println();
        System.out.println("===== QUEUE PATTERN: addLast() + removeFirst() =====");
        double arrayListQueueMs = time("ArrayList  queue", () -> {
            for (int i = 0; i < QUEUE_REPETITIONS; i++) {
                arrayListQueue.addLast(replacement);
                arrayListQueue.removeFirst();
            }
        });
        double linkedListQueueMs = time("LinkedList queue", () -> {
            for (int i = 0; i < QUEUE_REPETITIONS; i++) {
                linkedListQueue.addLast(replacement);
                linkedListQueue.removeFirst();
            }
        });
        System.out.println("Winner: " + (arrayListQueueMs < linkedListQueueMs ? "ArrayList" : "LinkedList"));

        // final (resize: crecer desde vacío, agregando siempre al final)
        Payment growSeed = payments(1).get(0);
        List<Payment> arrayListGrowing = new ArrayList<>();
        List<Payment> linkedListGrowing = new LinkedList<>();

        long arrayListWorstNanos = 0;
        for (int i = 0; i < RESIZE_OPERATIONS; i++) {
            long start = System.nanoTime();
            arrayListGrowing.add(growSeed);
            long elapsed = System.nanoTime() - start;
            if (elapsed > arrayListWorstNanos) arrayListWorstNanos = elapsed;
        }

        long linkedListWorstNanos = 0;
        for (int i = 0; i < RESIZE_OPERATIONS; i++) {
            long start = System.nanoTime();
            linkedListGrowing.add(growSeed);
            long elapsed = System.nanoTime() - start;
            if (elapsed > linkedListWorstNanos) linkedListWorstNanos = elapsed;
        }

        System.out.println();
        System.out.println("===== WORST SINGLE addLast() CALL, GROWING FROM EMPTY =====");
        System.out.printf("ArrayList  worst call: %.3f ms%n", arrayListWorstNanos / 1_000_000.0);
        System.out.printf("LinkedList worst call: %.3f ms%n", linkedListWorstNanos / 1_000_000.0);
        System.out.println("Winner (most consistent): " + (linkedListWorstNanos < arrayListWorstNanos ? "LinkedList" : "ArrayList"));

        // summary
        System.out.println();
        System.out.println("===== SUMMARY (all times in seconds) =====");
        System.out.printf("get(middle):   ArrayList=%.2fs  LinkedList=%.2fs%n", arrayListGetMs / 1000, linkedListGetMs / 1000);
        System.out.printf("set(middle):   ArrayList=%.2fs  LinkedList=%.2fs%n", arrayListSetMs / 1000, linkedListSetMs / 1000);
        System.out.printf("insert(0):     ArrayList=%.2fs  LinkedList=%.2fs%n", arrayListInsertMs / 1000, linkedListInsertMs / 1000);
        System.out.printf("delete(value): ArrayList=%.2fs  LinkedList=%.2fs%n", arrayListDeleteMs / 1000, linkedListDeleteMs / 1000);
        System.out.printf("queue:         ArrayList=%.2fs  LinkedList=%.2fs%n", arrayListQueueMs / 1000, linkedListQueueMs / 1000);
        System.out.printf("resize worst:  ArrayList=%.3fms LinkedList=%.3fms%n",
                arrayListWorstNanos / 1_000_000.0, linkedListWorstNanos / 1_000_000.0);
    }
}