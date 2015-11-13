package com.nixsolutions;

import java.io.IOException;

/**
 * Created by kozlovskij on 11/13/2015.
 */
public class Test {
    public static void main(String[] args) {
        CustomSave test = new CustomSave();
        try {
            test.save("test", "D:\\test.txt");
            test.save("test1", "");
            test.save("test2", "D:\\test.txt");
        } catch (RuntimeException e) {
            System.out.printf("We caught exception: %s%n", e.toString());
        }
    }
}
