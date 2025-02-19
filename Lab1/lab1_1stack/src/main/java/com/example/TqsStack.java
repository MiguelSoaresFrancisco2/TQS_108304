package com.example;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack <T> {
    private LinkedList<T> stack;
    private int max_len = 5;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public TqsStack() {
        this.stack = new LinkedList();
    }

    public void push(T obj) {
        if (this.stack.size() == max_len) {
            throw new IllegalStateException("Stack is full");
        } else {
            this.stack.addLast(obj);
        }

    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");

        } else {
            return stack.removeLast();

        }
    }

    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    public int size() {
        return this.stack.size();
    }

    public T peek() {
        return this.stack.getLast();

    }

    public T popTopN(int n){
        if (n<=0 || n>stack.size()) {
            throw new IllegalArgumentException("Invalid number of element to pop");
        }
        T top =null;
        for(int i=0;i<n;i++){
            top =stack.removeFirst();
            }
        return top;
    }
}
