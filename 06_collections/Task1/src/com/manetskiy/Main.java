package com.manetskiy;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        MyCollection<String> my = new MyCollection<>(10);
        my.add("a");
        my.add("b");
        my.add("c");
        my.add("d");

        System.out.println("Collection size before removing all elements: " + my.size());

        Iterator<String> iterator = my.iterator();

        while (iterator.hasNext()) {
            String s = iterator.next();
            iterator.remove();
            System.out.print(s + " ");
        }

        System.out.println();
        System.out.println("Collection size after removing all elements: " + my.size());


    }
}
