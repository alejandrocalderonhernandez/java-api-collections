package com.debuggeandoideas.api_collections.list;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ListOf {



    public static void main(String[] args) {
        List<String> list = new LinkedList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I"));

        // List.of(list).forEach(System.out::println); <- Inmutable

        list.add("1");
        list.add("2");
        list.add("3");

        list.remove(0);


        list.forEach(System.out::println);
    }
}
