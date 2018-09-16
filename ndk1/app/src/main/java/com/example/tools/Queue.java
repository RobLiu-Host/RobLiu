package com.example.tools;

import java.util.Stack;

/**
 * 用两个栈实现一个队列 并且支持push poll  peek pop
 * <p>
 * peek 获取队列第一个元素 获取队列栈顶的数据
 * <p>
 * poll 获取队列第一个元素并移除 获取队列栈顶的数据并移除
 *
 *
 *
 *
 */
public class Queue<T> {

    private Stack<T> stackData;

    private Stack<T> stackQueue;

    public Queue() {
        this.stackData = new Stack<>();
        this.stackQueue = new Stack<>();
    }

    /**
     * 队列里添加元素
     *
     * @param item
     */
    public void push(T item) {
        stackData.push(item);
        pushToQueue();
    }

    /**
     * 压入队列栈
     */
    private void pushToQueue() {
        if (stackQueue.isEmpty()) {
            while (!stackData.isEmpty()) {
                //获取数据栈栈顶的数据 并移除
                stackQueue.push(stackData.pop());
            }
        }
    }

    /**
     * 获取栈顶数据并移除
     *
     * @return
     */
    public T poll() {
        if (stackQueue.isEmpty() && stackData.isEmpty()) {
            throw new RuntimeException("Queue is isEmpty");
        }
        T value = stackQueue.pop();
        pushToQueue();
        return value;
    }

    /**
     * 获取队列的第一个元素
     *
     * @return
     */
    public T peek() {
        if (stackQueue.isEmpty() && stackData.isEmpty()) {
            throw new NullPointerException("Queue is isEmpty");
        }
        return stackQueue.peek();
    }

    public T get(int i) {
        if (stackQueue.isEmpty()) {
            throw new NullPointerException("Queue is isEmpty");
        }
        pushToQueue();
        if (i == 0) {
            return stackQueue.peek();
        }
        return stackData.get(i - 1);
    }

    /**
     * 返回值长度
     *
     * @return
     */
    public int size() {
        return stackData.size() + 1;
    }
}
