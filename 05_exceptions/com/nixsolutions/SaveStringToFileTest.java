package com.nixsolutions;

/**
 * Created by Rybkinrolla on 14.11.2015.
 */
public class SaveStringToFileTest {
    public static void main(String[] args) {
        SaveStringToFileLog sstf = new SaveStringToFileLog();
        sstf.save("Trying to save this", "D:/saveTester.txt");
        sstf.save("Second try", "Z:/saveTester.txt");
        sstf.save("", "D:/saveTester.txt");
    }
}
