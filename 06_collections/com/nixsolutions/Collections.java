package com.nixsolutions;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sergey on 13.11.2015.
 */
public class Collections {
    public static void main(String[] args) {
        Collection <Object> collection = new MyCollection();
        Object test = new Circle();
        Object test1 = new Triangle();
        collection.add(test);
        collection.add(test1);
        System.out.println(collection.size());
        collection.remove(test);
        System.out.println(collection.size());
        //collection.clear();
        Collection<Object> collection1 = new MyCollection();
        System.out.println(collection1.addAll(collection));
        System.out.println(collection1.size());
    }
}
