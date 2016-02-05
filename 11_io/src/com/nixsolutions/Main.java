package com.nixsolutions;

import java.io.IOException;

/**
 * Created by sobolenko on 2/4/2016.
 */
public class Main {
    public static void main(String[] args) {
        FileCopy fileCopy = new FileCopy();
        try {
            fileCopy.makeCopyWithJavaIo("D:\\Framework", "D:\\fz");
            //fileCopy.makeCopyWithJavaNio("D:\\Framework", "D:\\fz");
            //fileCopy.makeCopyWithApacheCommonsIo("D:\\Framework", "D:\\fz");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
