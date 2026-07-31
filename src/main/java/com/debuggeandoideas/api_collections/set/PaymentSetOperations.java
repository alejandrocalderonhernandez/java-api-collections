package com.debuggeandoideas.api_collections.set;

import java.util.HashSet;
import java.util.Set;

public final class PaymentSetOperations {

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [3, 4, 5]
    public Set<String> matchedReferences(Set<String> systemA, Set<String> systemB) {
        Set<String> matchedReferences = new HashSet<>(systemA);
        matchedReferences.retainAll(systemB);
        return matchedReferences;
    }

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [1, 2]
    public Set<String> missingFromSystemB(Set<String> systemA, Set<String> systemB) {
        Set<String> missingFromSystemB = new HashSet<>(systemA);
        missingFromSystemB.removeAll(systemB);
        return missingFromSystemB;
    }

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [6, 7]
    public Set<String> extraInSystemB(Set<String> systemA, Set<String> systemB) {
        Set<String> extraSystemB = new HashSet<>(systemB);
        extraSystemB.removeAll(systemA);
        return extraSystemB;
    }

    // System A = [1, 2, 3, 4, 5]
    // System B = [3, 4, 5, 6, 7]
    // Result   = [1, 2, 3, 4, 5, 6, 7]
    public Set<String> allReferences(Set<String> systemA, Set<String> systemB) {
        Set<String> allReferences = new HashSet<>(systemA);
        allReferences.addAll(systemB);
        return allReferences;
    }
}