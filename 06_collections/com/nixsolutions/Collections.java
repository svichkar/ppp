package com.nixsolutions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/**
 * Created by Sergey on 13.11.2015.
 */
public class Collections {
    public static void main(String[] args) {
        Collection<Object> collection = new MyCollection();
        Random random = new Random();
        int type;
        for (int i = 0; i < 10; i++) {
            type = random.nextInt(3);
            switch (type) {
                case 0:
                    collection.add(new Circle());
                    break;
                case 1:
                    collection.add(new Square());
                    break;
                case 2:
                    collection.add(new Triangle());
                    break;
                default:
                    System.err.println("Error in switch");
                    break;
            }
        }
        System.out.println(collection.size());
        collection.iterator().remove();
        System.out.println(collection.size());
        collection.iterator().remove();
        System.out.println(collection.size());
        System.out.println(collection.add(new Circle()));
        System.out.println(collection.size());
        while (collection.iterator().hasNext()) {
            System.out.println(collection.iterator().next().toString());
        }
    }
}


