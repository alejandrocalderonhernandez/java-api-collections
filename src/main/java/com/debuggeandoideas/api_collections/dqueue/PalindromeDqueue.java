package com.debuggeandoideas.api_collections.dqueue;

import java.util.ArrayDeque;
import java.util.Deque;

// ---------------------------------------------------------------
// Deque
// offerLast()  → comportamiento de COLA — inserta por atrás
// offerFirst() → comportamiento de PILA — inserta por adelante
// pollFirst()  → comportamiento de COLA — saca por el frente (IZQUIERDA)
// pollLast()   → comportamiento de PILA — saca por atrás (DERECHA)
// peekFirst()  → mira el frente (IZQUIERDA) sin quitarlo
// peekLast()   → mira el final (DERECHA) sin quitarlo
// ---------------------------------------------------------------
public class PalindromeDqueue {

    public boolean isPalindrome(String s) {
        String normalized = normalize(s);
        Deque<Character> deque = toDeque(normalized);

        while (deque.size() > 1) {
            char left = deque.pollFirst();
            char right = deque.pollLast();

            if (left != right) {
                return false;
            }
        }
        return true;
    }

    private static String normalize(String s) {
        StringBuilder normalized = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                normalized.append(Character.toLowerCase(c));
            }
        }
        return normalized.toString();
    }

    private static Deque<Character> toDeque(String normalized) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : normalized.toCharArray()) {
            deque.offerLast(c);
        }
        return deque;
    }

}


