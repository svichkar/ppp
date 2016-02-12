package com.nixsolutions;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

/**
 * @author Sirotkin Mikhail
 * Class describes Consumer that catch even or odd numbers from queue and write it to console immediatly
 * Using parametr evenIsWaiting you should setup expected type of numbers (true - Even, false - Odd)
 * */
public class Consumer implements Runnable {

    private BlockingQueue queue;
    private boolean evenIsWaiting;
    private boolean needContinue;
    private ArrayList list;

    /**
     * Constructor of Consumer class
     *
     * @param queue
     * @param evenIsWaiting
     */
    public Consumer(BlockingQueue queue, boolean evenIsWaiting) {
        this.queue = queue;
        this.evenIsWaiting = evenIsWaiting;
        needContinue = true;
        list = new ArrayList();
    }

    @Override
    public void run() {
        while (needContinue) {
            try {
                if(!consume((int) queue.peek()))
                    Thread.sleep(10); //provide resources(queue) to other thread if it's not suit us (not expected)
            } catch (NullPointerException ne) {
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
        }
    }

    /**
     * Consume method
     * Check if value from queue is even or odd and catch it if it is like expected one (see evenIsWaiting)
     */
    private boolean consume(int numb) throws InterruptedException {
        boolean isEven = numb % 2 == 0;
        if (evenIsWaiting == isEven){
            list.add(numb);
            System.out.println((isEven ? "Even" : "Odd") + " value: " + queue.take());
        }
        return evenIsWaiting == isEven;
    }

    /**
     * Print to console count of cought numbers with their type
     */
    public void printCountOfCoghtNumbers() {
        System.out.printf("We cought " + list.size() + " " + (evenIsWaiting ? "even" : "odd") + " numbers from queue \n");
    }
}
