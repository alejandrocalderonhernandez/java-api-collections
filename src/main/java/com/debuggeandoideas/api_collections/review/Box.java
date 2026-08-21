package com.debuggeandoideas.api_collections.review;

public class Box<T> {

    private T content;

    public void save(T object) {
        this.content = object;
    }

    public Object open() {
        return content;
    }
}