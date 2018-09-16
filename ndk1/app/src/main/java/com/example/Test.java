package com.example;

import com.example.tools.Queue;
import com.example.tools.RecursiveStack;

import java.util.Stack;

public class Test {


    public static void main(String[] args) {
        Stack<Integer> integerQueue = new Stack<>();
        integerQueue.push(1);
        integerQueue.push(2);
        integerQueue.push(3);



        RecursiveStack.reverse(integerQueue);
        for (Integer integer:integerQueue) {
            System.out.println("integer:"+integer);
        }
    }
}
