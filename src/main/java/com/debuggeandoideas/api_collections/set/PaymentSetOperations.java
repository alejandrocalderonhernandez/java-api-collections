package com.debuggeandoideas.api_collections.set;

import java.util.Set;

public final class PaymentSetOperations {

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [3, 4, 5]
    public Set<String> matchedReferences(Set<String> systemA, Set<String> systemB) {
        throw new UnsupportedOperationException();
    }

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [1, 2]
    public Set<String> missingFromSystemB(Set<String> systemA, Set<String> systemB) {
        throw new UnsupportedOperationException();
    }

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [6, 7]
    public Set<String> extraInSystemB(Set<String> systemA, Set<String> systemB) {
        throw new UnsupportedOperationException();
    }

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [1, 2, 3, 4, 5, 6, 7]
    public Set<String> allReferences(Set<String> systemA, Set<String> systemB) {
        throw new UnsupportedOperationException();
    }
}