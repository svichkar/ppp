package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Rybkinrolla on 14.11.2015.
 */
public class SaveStringToFileLogTest {
    private static final Logger customLog = LogManager.getLogger(SaveStringToFileLogTest.class);
    public static void main(String[] args) {
        try {
            SaveStringToFileLog sstf = new SaveStringToFileLog();
            customLog.entry();
            sstf.save("Trying to save this", "D:/saveTester.txt");
            customLog.info("Save successful");
            sstf.save("Second try", "Z:/saveTester.txt");
        } catch (RuntimeException e){
            customLog.error("Gotcha inside main!", e);
        }
    }
}
