package com.example.tools;


import java.util.Stack;

/**
 * 实现一个获取到最小值的栈
 * <p>
 * 方式一  先判断最少值的栈是否为空(表示第一次入栈) 当》1次入栈时  判断是否小于或者等于最少值的栈栈顶的值 为真->压入最少值的栈     ->压入数据栈
 * <p>
 *      弹出 当数据栈弹出时 判断弹出的值与最少值栈顶的值是否相等 如果相等最小值的栈也弹出
 * <p>
 * 方式二 判断最少值的栈是否为空(表示第一次入栈) 当》1次入栈时 判断当前值是否小于栈顶的值 如果是最小值的栈压入当前值 如果不是 重复压入栈顶的值  ->压入数据栈
 * <p>
 *       弹出 当数据栈弹出时 最少值的栈 直接弹出
 *
 *       优劣 时间复杂度都是o(1) 空间复杂度都是O(n)
 *
 *       第一种压入快 弹出耗时
 *       第二种方式 弹出快 压入稍微耗时
 */
public class MinStack {

    private Stack<Integer> stackData;

    private Stack<Integer> stackMin;


    public MinStack() {
        this.stackData = new Stack<>();
        this.stackMin = new Stack<>();
    }

    /**
     * 数据压入 最小值
     *
     * @param element
     */
    public void add(Integer element) {
        if (stackMin.isEmpty()) {
            stackMin.add(element);
        } else if (element <= getMin()) {
            stackMin.push(element);
        }
        stackData.add(element);
    }

    /**
     * 弹出
     */
    public int pop() {
        if (stackData.isEmpty()) {
            throw new RuntimeException(" this stack isEmpty");
        }
        int value = stackData.pop();
        if (value == getMin()) {
            stackMin.pop();
        }
        return value;
    }

    /**
     * 返回这个栈顶的值
     *
     * @return
     */
    public int getMin() {
        return stackMin.peek();
    }
}
