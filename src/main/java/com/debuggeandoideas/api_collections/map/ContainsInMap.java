package com.debuggeandoideas.api_collections.map;

import java.util.HashMap;
import java.util.Map;

public class ContainsInMap {

    public static void main(String[] args) {
        Map<String, Integer> edades = new HashMap<>();
        edades.put("Ana", 28);
        edades.put("Beto", 35);
        edades.put("Caro", 22);

        System.out.println(edades.containsKey("Ana"));     // true  — "Ana" es una llave
        System.out.println(edades.containsKey("Dani"));   // false — "Dani" no está

        System.out.println(edades.containsValue(28));     // true  — 28 es el valor de "Ana"
        System.out.println(edades.containsValue(99));      // false — nadie tiene 99 años
    }
}
