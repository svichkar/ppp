/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Mednor
 */
public class DelayedWorker implements Runnable {

    private AtomicInteger counter;
    private int countStartFrom;

    public AtomicInteger getCounter() {
        return counter;
    }

    public int getCountStartFrom() {
        return countStartFrom;
    }

    public DelayedWorker(AtomicInteger counter, int countStartFrom) {
        this.counter = counter;
        this.countStartFrom = countStartFrom;
    }

    @Override
    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            if (this.getCounter().get() >= this.getCountStartFrom()) {
                System.out.println("Delayed worker (started after " + this.countStartFrom
                        + " iteration(s)) is working fine; Current interation is:"
                        + this.getCounter());
            }
        }
    }

}
