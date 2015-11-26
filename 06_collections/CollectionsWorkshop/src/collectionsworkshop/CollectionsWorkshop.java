/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package collectionsworkshop;

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

        MyPainfullCollection<Integer> myCollection = new MyPainfullCollection<>();
        myCollection.add(1);
        myCollection.add(2);
        myCollection.add(3);
        myCollection.add(4);
        myCollection.add(5);
        boolean a = myCollection.remove(8);
        myCollection.size();
        //Try methods here
    }

}
