package com.example.tools;

import java.util.Stack;

/**
 * 递归栈 倒置一个栈 运用递归的方法倒置一个栈
 */
public class RecursiveStack<E> {


    /**
     * 通过递归获取栈底的元素
     * @param stack
     * @param <E>
     * @return
     */
    private static <E> E getLastElement(Stack<E> stack) {
        //获取栈顶的元素
        E e = stack.pop();
        if (stack.isEmpty()) {
            return e;
        } else {
            E Last = getLastElement(stack);
            stack.push(e);
            return Last;
        }
    }

    /**
     *
     * @param stack
     * @param <E>
     */
    public static <E> void reverse(Stack<E> stack) {
        if (stack.isEmpty()) {
            return;
        }
        E e = getLastElement(stack);
        reverse(stack);
        stack.push(e);
    }
}
