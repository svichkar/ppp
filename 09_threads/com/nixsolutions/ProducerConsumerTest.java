package com.nixsolutions;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Rybkinrolla on 20.11.2015.
 */
public class ProducerConsumerTest {
    public static void main(String[] args) throws InterruptedException{
        BlockingQueue<Integer> arrOfInt = new LinkedBlockingQueue<>();
        Thread prod = new Thread(new CustomProducer(arrOfInt));
        Thread consEven = new Thread(new CustomConsumer(arrOfInt,true));
        Thread consOdd = new Thread(new CustomConsumer(arrOfInt,false));
        consEven.start();
        consOdd.start();
        prod.start();
        prod.join();
        while(arrOfInt.size()!= 0){}
        CustomConsumer.stopAllConsumers();
    }
}
