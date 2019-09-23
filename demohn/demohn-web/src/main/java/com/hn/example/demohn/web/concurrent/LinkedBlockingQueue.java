package com.hn.example.demohn.web.concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: liyb
 * @Date: 2019/9/18
 * @description
 */
public class LinkedBlockingQueue<E> {
    private static AtomicInteger count = new AtomicInteger();

    private Node<E> head;
    private Node<E> tail;
    private Integer capacity;
    private static final ReentrantLock takeLock = new ReentrantLock();
    private static final Condition NOTEMPTY = takeLock.newCondition();
    private static final ReentrantLock putLock = new ReentrantLock();
    private static final Condition NOTFULL = putLock.newCondition();
    public LinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }
    public LinkedBlockingQueue(Integer capacity) {
        if (capacity<0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        head=tail= new Node<>(null);
    }

    class Node<E> {
        E obj;
        Node<E> next;
        public Node(E obj) {
            this.obj = obj;
        }
    }
    public void put(E e) throws InterruptedException {
        putLock.lock();
        putLock.lockInterruptibly();
        try {
            while (count.get()==capacity){
                NOTFULL.await();
            }
            Node<E> node = new Node<>(e);
            tail = tail.next=node;
            count.getAndIncrement();
            NOTEMPTY.signalAll();
        } finally {
            putLock.unlock();
        }
    }
    public E take() throws InterruptedException {
        takeLock.lock();
        takeLock.lockInterruptibly();
        try {
            while (count.get()==0) {
                NOTEMPTY.await();
            }
            Node node = head;
            Node first = node.next;
            node.next = node;
            E obj = (E) first.obj;
            head=first.next;
            count.getAndDecrement();
            NOTFULL.signalAll();
            return obj;
        } finally {
            takeLock.unlock();
        }
    }
}
