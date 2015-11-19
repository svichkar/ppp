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
     * Collections
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        NullableCollection myCollection = new NullableCollection();
        myCollection.add("test");
        for (int i = 0; i < 10; i++) {
            myCollection.add(String.valueOf(new Random().nextInt()));
        }
        myCollection.add(null);
        myCollection.contains(null);
        myCollection.removeAll(null);
        myCollection.contains(null);

        //Try methods here
    }

}
