/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsworkshop;

/**
 *
 * @author mednorcom
 */
public class AsyncEvenSort {

    private static String[] queue = null;
    public static int queueLength = 1000;
    public static int consumerCount = 2;
    public static int producerCount = 1;

    protected static String[] getQueue() {
        return queue;
    }

    protected static void setQueue(String[] queue) {
        AsyncEvenSort.queue = queue;
    }
    
    

}
