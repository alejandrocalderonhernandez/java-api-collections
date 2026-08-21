package com.debuggeandoideas.api_collections.review;

public class ArrayReview {
    public static void main(String[] args) {
        String[] fruits = new String[4];
        fruits[0] = "apple";
        fruits[1] = "banana";
        fruits[2] = "grape";
        fruits[3] = "orange";

        String[] fruits2 = new String[10];

        for (int i = 0; i < fruits.length; i++) {
            fruits2[i] = fruits[i];
        }

        fruits2[4] = "lemon";
        fruits2[5] = "melon";

        printArray(fruits2);

    }


    private static void printArray(String[] arr) {
        for (int i = 0; i < arr.length; i++) {

           if (arr[i] != null) {
               System.out.printf("Index[%d] Value = %s%n", i, arr[i].toUpperCase());
           } else {
               System.out.printf("Index[%d] Value = NULL!"  + "\n", i);
           }
        }
    }
}