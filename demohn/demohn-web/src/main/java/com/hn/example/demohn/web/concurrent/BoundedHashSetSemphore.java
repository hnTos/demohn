package com.hn.example.demohn.web.concurrent;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;
/**
 * @Author: liyb
 * @Date: 2019/9/18
 * @description
 */
public class BoundedHashSetSemphore {
    private final Set set;
    private final Semaphore sem;

    public BoundedHashSetSemphore(int bound){
        this.set = Collections.synchronizedSet(new HashSet());
        sem = new Semaphore(bound);
    }
    public boolean add(Object o) throws InterruptedException{
        sem.acquire();
        boolean wasAdded = false;
        try{
            wasAdded = set.add(o);
        }finally{
            sem.release();
        }
        return wasAdded;
    }
    public boolean remove(Object o) throws InterruptedException{
        sem.acquire();
        boolean wasRemoved = set.remove(o);
        if(wasRemoved){
            sem.release();
        }
        return wasRemoved;
    }
    public static void main(String []args) throws InterruptedException{
        
        int n=0;
        ///n=1,2分别有不同的输出,可以观察其中的变化
        BoundedHashSetSemphore bs = new BoundedHashSetSemphore(n);
        if(bs.remove("213")){
            System.out.println("object has been removed");
        }else{
            System.out.println("waiting .........");
        }
        System.out.println("remove "+bs.set);
        if(bs.add("213")){
            System.out.println("add successful");
        }else{
            System.out.println("waiting for add ........");
        }
        System.out.println("add:"+bs.set);
        if(bs.remove("213")){
            System.out.println("object has been removed");
        }else{
            System.out.println("waiting .........");
        }
        System.out.println("remove after add :"+bs.set);
    }
}
