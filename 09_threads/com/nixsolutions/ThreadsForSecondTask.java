package com.nixsolutions;


/**
 * Created by Sergey on 21.11.2015.
 */
public class ThreadsForSecondTask implements Runnable {
    private String name;

    public ThreadsForSecondTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
            System.out.println(name + ": I'm working");
    }
}
