package com.oddeven;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by pantiukhin on 2/12/2016.
 */
public class ProducerConsumerStart {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<Integer>();
        Thread producer = new Thread(new Producer(queue));
        Thread consumerOne = new Thread(new ConsumerOne(queue));
        Thread consumerTwo = new Thread(new ConsumerTwo(queue));
        producer.start();
        consumerOne.start();
        consumerTwo.start();
    }
}
