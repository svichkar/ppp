package com.nixsolutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pantiukhin on 2/3/2016.
 */
public class CollectionManipulator {
    public static void main(String[] args) {
        //Working with the add() method
        CollectionImplementation implementation = new CollectionImplementation();
        CollectionManipulator manipulator = new CollectionManipulator();
        //Working with the add() method
        List<String> list = new ArrayList<String>();
        list.add("William");
        list.add("Barry");
        implementation.addAll(list);
        manipulator.displayResult("Working with the addAll method:", implementation);
        // Working with the clear() method
        implementation.clear();
        manipulator.displayResult("Working with the clear() method", implementation);
        //Working with the contains method
        System.out.println("============================");
        System.out.println("Working with the contains method");
        System.out.println("============================");
        implementation.add("Frank");
        System.out.println(implementation.contains("Frank"));
        System.out.println(implementation.contains("John"));
        System.out.println("============================");
        System.out.println("Working with the containsAll method");
        //Working with the containsAll method
        implementation.addAll(list);
        System.out.println(implementation.containsAll(list));
        List<String> newList = new ArrayList<String>();
        newList.add("Hugh");
        newList.add("Samantha");
        System.out.println(implementation.containsAll(newList));
        //Working with the isEmpty() method
        implementation.clear();
        System.out.println("============================");
        manipulator.displayResult("Working with the isEmpty() method:", implementation);
        System.out.println(implementation.isEmpty());
        System.out.println("============================");
        //Working with the remove() method
        implementation.add("Jessica");
        implementation.add("Brian");
        implementation.remove("Brian");
        manipulator.displayResult("Working with the remove() method:", implementation);
        //Working with the removeAll() method
        implementation.addAll(list);
        implementation.removeAll(list);
        manipulator.displayResult("Working with the removeAll() method:", implementation);
        //Working with the retainAll() method
        implementation.addAll(list);
        implementation.add("Jennifer");
        implementation.retainAll(list);
        manipulator.displayResult("Working with the retainAll() method:", implementation);
        //Working with the size() method
        System.out.println("Working with the size() method:");
        System.out.println(implementation.size());
        System.out.println("============================");
        //Working with the Object[] toArray() method
        System.out.println("============================");
        System.out.println("Working with the toArray() and iterator() methods:");
        Iterator iterator = implementation.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("============================");
    }

    public void displayResult(String message, Collection endCollection) {
        System.out.println(message);
        for (Object obj : endCollection) {
            System.out.println(obj);
        }
        System.out.println("============================");
    }
}
