package com.example.ndk1;

import com.example.entity.ClassB2Interface;
import com.example.entity.Singleton;

public class AMN {


    private static Singleton<ClassB2Interface> gDefault = new Singleton<ClassB2Interface>() {
        @Override
        public ClassB2Interface createObject() {
            ClassB2Interface classB2Interface = new ClassB2Interface();
            classB2Interface.id = 2;
            return classB2Interface;
        }
    };


    public static ClassB2Interface getDefault() {
        return gDefault.get();
    }
}
