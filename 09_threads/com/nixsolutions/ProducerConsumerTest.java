package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Rybkinrolla on 20.11.2015.
 */
public class ProducerConsumerTest {
    public static void main(String[] args){
        BlockingQueue<Integer> arrOfInt = new LinkedBlockingQueue<>();
        ReentrantLock lock = new ReentrantLock(true);
        CustomConsumer c1 = new CustomConsumer(arrOfInt,lock,true);
        CustomConsumer c2 = new CustomConsumer(arrOfInt,lock,false);
        CustomProducer p1 = new CustomProducer(arrOfInt);
        Thread prod = new Thread(p1);
        Thread consEven = new Thread(c1);
        Thread consOdd = new Thread(c2);
        consEven.start();
        consOdd.start();
        prod.start();
        try {
            Thread.currentThread().sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c1.stopConsume();
        c2.stopConsume();
    }
}
