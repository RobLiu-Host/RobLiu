package com.example.entity;

public abstract class Singleton<T> {


    T mInstance;


    public abstract T createObject();


    public T get() {
        return mInstance;
    }
}
