package com.debuggeandoideas.api_collections.set;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentSetOperationsTest {

    private final PaymentSetOperations operations = new PaymentSetOperations();

    @Test
    void matchedReferencesReturnsOnlyPaymentsConfirmedByBoth() {
        Set<String> systemA = new HashSet<>(Set.of("PAY-001", "PAY-002", "PAY-003"));
        Set<String> systemB = new HashSet<>(Set.of("PAY-002", "PAY-003", "PAY-004"));

        Set<String> matched = operations.matchedReferences(systemA, systemB);

        assertEquals(Set.of("PAY-002", "PAY-003"), matched);
    }

    @Test
    void missingFromSystemBDetectsPaymentsNeverConfirmed() {
        Set<String> systemA = new HashSet<>(Set.of("PAY-001", "PAY-002"));
        Set<String> systemB = new HashSet<>(Set.of("PAY-002"));

        Set<String> missing = operations.missingFromSystemB(systemA, systemB);

        assertEquals(Set.of("PAY-001"), missing);
    }

    @Test
    void extraInSystemBDetectsPaymentsWithNoRecordInSystemA() {
        Set<String> systemA = new HashSet<>(Set.of("PAY-001"));
        Set<String> systemB = new HashSet<>(Set.of("PAY-001", "PAY-999"));

        Set<String> extra = operations.extraInSystemB(systemA, systemB);

        assertEquals(Set.of("PAY-999"), extra);
    }

    @Test
    void allReferencesCombinesBothWithoutDuplicating() {
        Set<String> systemA = new HashSet<>(Set.of("PAY-001", "PAY-002"));
        Set<String> systemB = new HashSet<>(Set.of("PAY-002", "PAY-003"));

        Set<String> all = operations.allReferences(systemA, systemB);

        assertEquals(Set.of("PAY-001", "PAY-002", "PAY-003"), all);
    }

    @Test
    void matchedAndMissingTogetherRecreateSystemA() {
        Set<String> systemAValues = Set.of("PAY-001", "PAY-002", "PAY-003");
        Set<String> systemBValues = Set.of("PAY-002", "PAY-003", "PAY-004");

        // Cada llamada recibe su PROPIA copia de los datos — así, sin
        // importar si el método muta lo que le pasan o no, una llamada
        // nunca puede contaminar a la otra.
        Set<String> matched = operations.matchedReferences(
                new HashSet<>(systemAValues), new HashSet<>(systemBValues));
        Set<String> missing = operations.missingFromSystemB(
                new HashSet<>(systemAValues), new HashSet<>(systemBValues));

        Set<String> reconstructed = new HashSet<>(matched);
        reconstructed.addAll(missing);

        assertEquals(systemAValues, reconstructed);
    }
}