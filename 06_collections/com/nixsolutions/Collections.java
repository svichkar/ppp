package com.nixsolutions;

import java.util.Random;

/**
 * Created by Sergey on 13.11.2015.
 */
public class Collections {
    public static void main(String[] args) {
        MyCollection collection = new MyCollection();
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

        MyCollection secondCollection = new MyCollection();
        for (int i = 0; i < 3; i++) {
            secondCollection.add(collection.get(i));
        }

        System.out.println(collection.size());

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(collection.get(i).toString());
        }

        collection.retainAll(secondCollection);

        System.out.println(collection.size());

        for (int i = 0; i < collection.size(); i++) {
            System.out.println(collection.get(i).toString());
        }
    }

}



