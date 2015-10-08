package com.nixsolutions;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class NewThreadProducer extends Thread {

    public static Queue<Integer> quene = new LinkedList<Integer>();
    public void run() {
	
	for (int i = 0; i < 100; i++) {
	    Random rn = new Random();
	    int temp = rn.nextInt(100);
	    quene.add(temp);
	    
	    System.out.println("Producer thread creates value - " + temp);
	}
	

    }

}
