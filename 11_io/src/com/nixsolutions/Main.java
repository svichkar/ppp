package com.nixsolutions;

import java.io.IOException;

/**
 * Created by sobolenko on 2/4/2016.
 */
public class Main {
    public static void main(String[] args) {
        FileCopy fileCopy = new FileCopy();
        try {
            //fileCopy.makeCopyWithJavaIo("D:\\s", "D:\\fz");
            fileCopy.makeCopyWithJavaNio("C:\\s", "C:\\fz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
