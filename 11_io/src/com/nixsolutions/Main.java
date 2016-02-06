package com.nixsolutions;

import java.io.IOException;

/**
 * Created by sobolenko on 2/4/2016.
 */
public class Main {
    public static void main(String[] args) {
        FileCopy fileCopy = new FileCopy();
        try {
            fileCopy.createDirectories();
            fileCopy.makeCopyWithJavaIo("testDir", "CopyByIo");
            fileCopy.makeCopyWithJavaNio(System.getProperty("user.dir") + "\\testDir", System.getProperty("user.dir") + "\\CopyByNio");
            fileCopy.makeCopyWithApacheCommonsIo("testDir", "CopyByApache");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
