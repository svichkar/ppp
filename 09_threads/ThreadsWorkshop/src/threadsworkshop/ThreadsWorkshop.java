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
public class ThreadsWorkshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        AsyncEvenSort sortEven = new AsyncEvenSort(1000, 100, 2, 1);
        sortEven.start();
        

        DelayedStart delayedStart = new DelayedStart();
        delayedStart.start();

    }

}
