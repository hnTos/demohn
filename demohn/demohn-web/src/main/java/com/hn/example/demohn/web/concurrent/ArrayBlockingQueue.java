package com.hn.example.demohn.web.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: liyb
 * @Date: 2019/9/18
 * @description
 */
public class ArrayBlockingQueue<E> {
    final Object[] items;
    int count;
    ReentrantLock lock = new ReentrantLock();
    private final Condition FULL;
    private final Condition EMPTY;
    private int takeIndex;
    private int putIndex;
   private static final int defaultSize = 16;
    public ArrayBlockingQueue(int capacity){
        items = new Object[capacity];
        EMPTY =lock.newCondition();
        FULL=lock.newCondition();
    }
    class Node{
    }
    public ArrayBlockingQueue(){
        this(defaultSize);
    }
    public void  put(E e) throws InterruptedException {
        lock.lock();
        lock.lockInterruptibly();
        while (count == items.length) {
            FULL.await();
        }
        try {
            items[putIndex] = e;
            count++;
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            EMPTY.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        try {
            lock.lock();
            lock.lockInterruptibly();
            while (count==0){
                EMPTY.await();
            }
            E item = (E)items[takeIndex];
            count--;
            if (++takeIndex == items.length) {
                takeIndex= 0;
            }
            FULL.signalAll();
            return item;
        }finally {
            lock.unlock();
        }
    }
}
