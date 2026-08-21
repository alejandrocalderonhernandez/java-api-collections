package com.debuggeandoideas.api_collections.review;

public class IntoBox {

    public static void main(String[] args) {
        Box<String> fruitBox1 = new Box<>();
        fruitBox1.save("banana");

        Box<Integer> fruitBox2 = new Box<>();
        fruitBox2.save(42);

        String fruit1 = (String) fruitBox1.open();
        Integer fruit2 = (Integer) fruitBox2.open();

        System.out.println(fruit1.toUpperCase());
        System.out.println(fruit2);
    }
}
