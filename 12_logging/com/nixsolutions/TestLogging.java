package com.nixsolutions;

/**
 * Created by kozlovskij on 11/13/2015.
 */
public class TestLogging {
    public static void main(String[] args) {
        CustomSaveLogging test = new CustomSaveLogging();
        try {
            test.save("test", "D:\\test.txt");
            test.save("test1", "");
            test.save("test2", "D:\\test.txt");
        } catch (RuntimeException e) {
            System.out.printf("We caught exception: %s%n", e.toString());
        }
    }
}
