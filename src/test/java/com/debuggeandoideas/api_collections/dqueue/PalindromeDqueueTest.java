package com.debuggeandoideas.api_collections.dqueue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PalindromeDqueueTest {

    private final PalindromeDqueue solution = new PalindromeDqueue();

    @Test
    void classicPhraseWithSpacesAndPunctuationIsPalindrome() {
        boolean result = solution.isPalindrome("A man, a plan, a canal: Panama");

        assertTrue(result);
    }

    @Test
    void phraseThatIsNotAPalindromeReturnsFalse() {
        boolean result = solution.isPalindrome("race a car");

        assertFalse(result);
    }

    @Test
    void emptyStringAfterCleaningIsPalindrome() {
        boolean result = solution.isPalindrome(" ");

        assertTrue(result);
    }
}