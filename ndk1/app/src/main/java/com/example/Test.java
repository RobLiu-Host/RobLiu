package com.example;

import com.example.tools.SortStack;

import java.util.Stack;

public class Test {


    public static void main(String[] args) {
        Stack<Integer> integerQueue = new Stack<>();
        integerQueue.push(4);
        integerQueue.push(5);
        integerQueue.push(1);
        integerQueue.push(3);
        integerQueue.push(2);

        SortStack.sort(integerQueue);
        for (Integer integer:integerQueue) {
            System.out.println("integer:"+integer);
        }
    }
}
