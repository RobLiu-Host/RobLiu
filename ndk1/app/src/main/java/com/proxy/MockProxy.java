package com.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理对象
 */
public class MockProxy implements InvocationHandler {


    Object target;

    public MockProxy(Object object) {
        this.target = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object object;
        Log.d("tag", "hello babe you are hook  start");
        object = method.invoke(target, args);
        Log.d("tag", "execute method behind");
        return object;
    }

}
