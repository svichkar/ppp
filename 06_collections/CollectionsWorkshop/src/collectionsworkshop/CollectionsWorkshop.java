/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionsworkshop;

import java.util.Random;

/**
 *
 * @author mednorcom
 */
public class CollectionsWorkshop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MySleeplessCollection myCollection = new MySleeplessCollection();
        myCollection.add("test");
        for (int i = 0; i < 1000; i++) {
            myCollection.add(String.valueOf(new Random().nextInt()));
        }

        myCollection.size();
        for (Object a : myCollection) {
            System.out.println(a.toString());
        }
        myCollection.add("rest");
        myCollection.contains("s");
        myCollection.contains("test");

        MySleeplessCollection myCollection2 = new MySleeplessCollection();
        myCollection2.add("rest");
        for (int i = 0; i < 1000; i++) {
            myCollection2.add(String.valueOf(new Random().nextInt()));
        }

        myCollection.removeAll(myCollection2);
        myCollection.addAll(myCollection2);
        myCollection2.clear();
        Object[] arrayOne = new Object[1500];
        Object[] arrayOneOut = myCollection.toArray(arrayOne);
        Object[] arrayTwo = new Object[500];
        Object[] arrayTwoOut = myCollection.toArray(arrayTwo);
        myCollection2.size();

    }

}
