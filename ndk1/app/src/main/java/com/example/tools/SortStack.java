package com.example.tools;


import java.util.Stack;

/**
 * 排序栈 要求只能加一个辅助栈 一个临时变量 完成排序
 */
public class SortStack {


    public static void sort(Stack<Integer> integerStack) {
        Stack<Integer> helpStack = new Stack<>();

        while (!integerStack.isEmpty()) {
            int current = integerStack.pop();
            while (!helpStack.isEmpty() && current >helpStack.peek()) {
                    integerStack.push(helpStack.pop());
            }
            helpStack.push(current);
        }
        while (!helpStack.isEmpty()){
            integerStack.push(helpStack.pop());
        }
    }
}
