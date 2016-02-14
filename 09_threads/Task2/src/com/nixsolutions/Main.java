package com.nixsolutions;

import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {

        AtomicInteger counter = new AtomicInteger(0);
        //start thread that will increment counter each 100 miliseconds and will start threads when counter become
        // equal 100, 300 and 500
        new Thread(new MainCounterThread(counter)).start();
    }
}

