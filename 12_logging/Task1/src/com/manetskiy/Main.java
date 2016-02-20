package com.manetskiy;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        FileSaver fileSaver = new FileSaver();
        logger.info("Saving data into the file.");
        fileSaver.save("AAA", "C:/zzz/z/t1.txt");
        logger.info("File has been saved successfully.");

    }
}
