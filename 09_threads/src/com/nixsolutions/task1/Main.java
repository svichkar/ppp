package com.nixsolutions.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by svichkar on 11/23/2015.
 */
public class Main {

    public static void main(String args[]) {

        Producer producer = new Producer();
        EvenConsumer evenConsumer = new EvenConsumer(producer.getQueue(), producer.getReady());
        OddConsumer oddConsumer = new OddConsumer(producer.getQueue(), producer.getReady());

        ExecutorService service = Executors.newFixedThreadPool(3);

        service.execute(producer);
        service.execute(oddConsumer);
        service.execute(evenConsumer);

        service.shutdown();
    }

}
