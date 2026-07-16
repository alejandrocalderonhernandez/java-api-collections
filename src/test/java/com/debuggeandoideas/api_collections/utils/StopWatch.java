package com.debuggeandoideas.api_collections.utils;

public final class StopWatch {

    private StopWatch() {
    }

    public static long measure(Runnable operation) {
        long start = System.nanoTime();
        operation.run();
        return (System.nanoTime() - start) / 1_000_000;
    }

    public static long measureAndPrint(String label, Runnable operation) {
        long millis = measure(operation);
        System.out.printf("%-32s %6d ms%n", label, millis);
        return millis;
    }
}